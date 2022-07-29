package hyun6ik.gridgetest.infrastructure.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.comment.constant.CommentStatus;
import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.member.entity.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hyun6ik.gridgetest.domain.comment.entity.QComment.*;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Comment> findByIdAndCommentId(Long memberId, Long commentId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(comment)
                        .innerJoin(comment.member, QMember.member)
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
}
