package hyun6ik.gridgetest.infrastructure.post.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostFeedDto;
import hyun6ik.gridgetest.interfaces.post.dto.response.QPostFeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.member.follow.QFollow.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;
import static hyun6ik.gridgetest.domain.post.image.QImage.*;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Post> findByIdAndMemberId(Long memberId, Long postId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(post)
                .innerJoin(post.member, member)
                .fetchJoin()
                .where(post.id.eq(postId), post.member.id.eq(memberId), post.postStatus.ne(PostStatus.DELETE))
                .fetchOne()
        );
    }

    public Optional<Post> findById(Long postId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(post)
                        .where(post.postStatus.eq(PostStatus.USE), post.id.eq(postId))
                        .fetchOne()
        );
    }

    public Optional<PostFeedDto> findPostFeedDtoBy(Long postId) {
        return Optional.ofNullable(
                queryFactory
                        .select(new QPostFeedDto(
                                post.id,
                                post.member.profile.nickName,
                                post.member.profile.image,
                                post.likes.likes.size(),
                                post.comments.comments.size()
                        ))
                        .from(post)
                        .innerJoin(post.member, member)
                        .where(post.id.eq(postId), post.postStatus.eq(PostStatus.USE))
                        .fetchOne()
        );
    }

    public List<String> findPostFeedDtoImagesBy(Long postId) {
        return queryFactory
                .select(image.url)
                .from(image)
                .innerJoin(image.post, post)
                .where(image.post.id.eq(postId), post.postStatus.eq(PostStatus.USE))
                .fetch();
    }

    public Page<PostFeedDto> findHomeFeedDtosBy(Long memberId, Pageable pageable) {
        final List<PostFeedDto> content = queryFactory
                .select(new QPostFeedDto(
                        post.id,
                        post.member.profile.nickName,
                        post.member.profile.image,
                        post.likes.likes.size(),
                        post.comments.comments.size()
                ))
                .from(post)
                .innerJoin(post.member, member)
                .where(post.postStatus.eq(PostStatus.USE), (JPAExpressions.select(follow.from.id)
                        .from(follow)
                        .where(follow.from.id.eq(memberId)).in(memberId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createTime.desc())
                .fetch();

        final int count = queryFactory
                .selectFrom(post)
                .innerJoin(post.member, member)
                .where(post.postStatus.eq(PostStatus.USE), (JPAExpressions.select(follow.from.id)
                        .from(follow)
                        .where(follow.from.id.eq(memberId)).in(memberId)))
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, count);
    }
}
