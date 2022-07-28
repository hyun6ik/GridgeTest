package hyun6ik.gridgetest.infrastructure.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.member.entity.QMember;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.QPost;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.domain.post.image.QImages;
import hyun6ik.gridgetest.domain.post.like.QLikes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;
import static hyun6ik.gridgetest.domain.post.image.QImages.*;

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
}
