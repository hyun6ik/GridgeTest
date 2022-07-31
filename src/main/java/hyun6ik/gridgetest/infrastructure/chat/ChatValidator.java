package hyun6ik.gridgetest.infrastructure.chat;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ChatException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.infrastructure.chat.repository.ChatQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatValidator {

    private final ChatQueryRepository chatQueryRepository;

    public void hasAlreadyChatRoom(Member host, Member guest) {
        if (chatQueryRepository.findBy(host, guest).isPresent()) {
            throw new ChatException(ErrorCode.ALREADY_CHATROOM);
        }
    }
}
