package hyun6ik.gridgetest.domain.chat.service;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatCreateDto;

public interface ChatService {
    ChatCreateDto.Response createChatRoom(Member fromMember, Member toMember);
}