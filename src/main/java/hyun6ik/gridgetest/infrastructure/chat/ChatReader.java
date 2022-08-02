package hyun6ik.gridgetest.infrastructure.chat;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.chat.repository.ChatQueryRepository;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatReader {

    private final ChatQueryRepository chatQueryRepository;

    public ChatRoom getChatRoomBy(Member host, Member guest) {
        return chatQueryRepository.findBy(host, guest)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CHAT_ROOM));
    }

    public List<ChatRoomDto> getChatRoomDtosBy(Long memberId, Long chatRoomId) {
        return chatQueryRepository.findChatRoomDtosBy(memberId, chatRoomId);
    }
}
