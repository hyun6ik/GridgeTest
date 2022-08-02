package hyun6ik.gridgetest.infrastructure.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.comment.constant.CommentStatus;
import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.interfaces.comment.dto.response.PostCommentResponseDto;
import hyun6ik.gridgetest.interfaces.comment.dto.response.QPostCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hyun6ik.gridgetest.domain.comment.entity.QComment.*;
import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Comment> findByIdAndCommentId(Long memberId, Long commentId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(comment)
                        .innerJoin(comment.member, member)
                        .fetchJoin()
                        .where(comment.id.eq(commentId), comment.member.id.eq(memberId), comment.commentStatus.eq(CommentStatus.USE))
                        .fetchOne()
        );
    }

    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(comment)
                        .where(comment.id.eq(commentId), comment.commentStatus.eq(CommentStatus.USE))
                        .fetchOne()
        );
    }

    public Page<PostCommentResponseDto> findPostCommentDtosBy(Long postId, Pageable pageable) {
        final List<PostCommentResponseDto> content = queryFactory
                .select(new QPostCommentResponseDto(
                        comment.member.id,
                        comment.id,
                        comment.commentLikes.commentLikes.size(),
                        comment.member.profile.image,
                        comment.member.profile.nickName,
                        comment.commentContent.content,
                        comment.createTime
                ))
                .from(comment)
                .innerJoin(comment.post, post)
                .innerJoin(comment.member, member)
                .where(comment.post.id.eq(postId), comment.commentStatus.eq(CommentStatus.USE))
                .orderBy(comment.createTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final int size = queryFactory
                .select(comment.count())
                .from(comment)
                .innerJoin(comment.post, post)
                .where(comment.commentStatus.eq(CommentStatus.USE), comment.post.id.eq(postId))
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, size);
    }
}
