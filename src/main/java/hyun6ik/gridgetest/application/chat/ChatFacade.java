package hyun6ik.gridgetest.application.chat;

import hyun6ik.gridgetest.domain.chat.service.ChatService;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatService chatService;
    private final MemberService memberService;

    @Transactional
    public ChatCreateDto.Response createChatRoom(Long memberId, Long guestId) {
        final Member host = memberService.getMemberBy(memberId);
        final Member guest = memberService.getMemberBy(guestId);
        return chatService.createChatRoom(host, guest);
    }
}
