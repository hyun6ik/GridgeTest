package hyun6ik.gridgetest.infrastructure.chat;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.infrastructure.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatStore {

    private final ChatRepository chatRepository;

    public ChatRoom store(ChatRoom initChatRoom) {
        return chatRepository.save(initChatRoom);
    }
}
