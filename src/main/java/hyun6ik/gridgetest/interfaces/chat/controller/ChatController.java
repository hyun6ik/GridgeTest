package hyun6ik.gridgetest.interfaces.chat.controller;

import hyun6ik.gridgetest.application.chat.ChatFacade;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatFacade chatFacade;

    @LoginUser
    @PostMapping("/new")
    public ResponseEntity<ChatCreateDto.Response> createChatRoom(@MemberId Long memberId, @Valid @RequestBody ChatCreateDto.Request request) {
        return ResponseEntity.ok(chatFacade.createChatRoom(memberId, request.getGuestId()));
    }

}
