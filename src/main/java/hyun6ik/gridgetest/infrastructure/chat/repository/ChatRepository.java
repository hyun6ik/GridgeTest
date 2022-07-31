package hyun6ik.gridgetest.infrastructure.chat.repository;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}
