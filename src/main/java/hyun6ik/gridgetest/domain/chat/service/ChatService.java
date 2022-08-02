package hyun6ik.gridgetest.domain.chat.service;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatCreateResponseDto;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatRoomDto;

import java.util.List;

public interface ChatService {
    ChatCreateResponseDto createChatRoom(Member fromMember, Member toMember);

    ChatRoom getChatRoomBy(Member host, Member guest);

    List<ChatRoomDto> getChatRoomDtosBy(Long memberId, Long chatRoomId);
}
