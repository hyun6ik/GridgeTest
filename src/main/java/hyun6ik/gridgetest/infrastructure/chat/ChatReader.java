package hyun6ik.gridgetest.infrastructure.chat;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.chat.repository.ChatQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatReader {

    private final ChatQueryRepository chatQueryRepository;

    public ChatRoom getChatRoomBy(Member host, Member guest) {
        return chatQueryRepository.findBy(host, guest)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CHAT_ROOM));
    }
}
