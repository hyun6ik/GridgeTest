package hyun6ik.gridgetest.interfaces.chat.controller;

import hyun6ik.gridgetest.application.chat.ChatFacade;
import hyun6ik.gridgetest.domain.chat.service.ChatService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatCreateDto;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatMessageDto;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatFacade chatFacade;
    private final ChatService chatService;

    @LoginUser
    @PostMapping("/new")
    public ResponseEntity<ChatCreateDto.Response> createChatRoom(@MemberId Long memberId, @Valid @RequestBody ChatCreateDto.Request request) {
        return ResponseEntity.ok(chatFacade.createChatRoom(memberId, request.getGuestId()));
    }

    @LoginUser
    @PostMapping("/send")
    public ResponseEntity<ChatMessageDto.Response> sendMessage(@MemberId Long memberId, @Valid @RequestBody ChatMessageDto.Request request) {
        return ResponseEntity.ok(chatFacade.sendMessage(memberId, request.getToId(), request.getMessage()));
    }

    @LoginUser
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<ChatRoomDto>> getChatRoomChats(@MemberId Long memberId, @PathVariable Long chatRoomId) {
        return ResponseEntity.ok(chatService.getChatRoomDtosBy(memberId, chatRoomId));
    }
}
