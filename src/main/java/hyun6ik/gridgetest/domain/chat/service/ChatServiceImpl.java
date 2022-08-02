package hyun6ik.gridgetest.domain.chat.service;

import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.infrastructure.chat.ChatReader;
import hyun6ik.gridgetest.infrastructure.chat.ChatStore;
import hyun6ik.gridgetest.infrastructure.chat.ChatValidator;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatCreateDto;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final ChatReader chatReader;
    private final ChatStore chatStore;
    private final ChatValidator chatValidator;

    @Override
    @Transactional
    public ChatCreateDto.Response createChatRoom(Member host, Member guest) {
        chatValidator.hasAlreadyChatRoom(host, guest);
        final ChatRoom initChatRoom = new ChatRoom(host, guest);
        final ChatRoom chatRoom = chatStore.store(initChatRoom);
        return new ChatCreateDto.Response(chatRoom.getId());
    }

    @Override
    public ChatRoom getChatRoomBy(Member host, Member guest) {
        return chatReader.getChatRoomBy(host, guest);
    }

    @Override
    public List<ChatRoomDto> getChatRoomDtosBy(Long memberId, Long chatRoomId) {
        return chatReader.getChatRoomDtosBy(memberId, chatRoomId);
    }

}
