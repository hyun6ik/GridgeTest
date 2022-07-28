package hyun6ik.gridgetest.infrastructure.post.repository;

import hyun6ik.gridgetest.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
