package hyun6ik.gridgetest.infrastructure.comment.repository;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
