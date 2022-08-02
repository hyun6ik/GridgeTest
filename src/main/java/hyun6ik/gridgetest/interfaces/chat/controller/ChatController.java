package hyun6ik.gridgetest.interfaces.chat.controller;

import hyun6ik.gridgetest.application.chat.ChatFacade;
import hyun6ik.gridgetest.domain.chat.service.ChatService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.chat.dto.request.ChatCreateRequestDto;
import hyun6ik.gridgetest.interfaces.chat.dto.request.ChatMessageRequestDto;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatCreateResponseDto;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatMessageResponseDto;
import hyun6ik.gridgetest.interfaces.chat.dto.response.ChatRoomDto;
import hyun6ik.gridgetest.interfaces.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "채팅방을 만드는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/new")
    public ApiResponse<ChatCreateResponseDto> createChatRoom(@MemberId Long memberId, @Valid @RequestBody ChatCreateRequestDto request) {
        return ApiResponse.success(chatFacade.createChatRoom(memberId, request.getGuestId()));
    }

    @Operation(summary = "채팅 메시지를 보내는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/send")
    public ApiResponse<ChatMessageResponseDto> sendMessage(@MemberId Long memberId, @Valid @RequestBody ChatMessageRequestDto request) {
        return ApiResponse.success(chatFacade.sendMessage(memberId, request.getToId(), request.getMessage()));
    }

    @Operation(summary = "채팅방의 채팅 내역들을 조회하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @GetMapping("/{chatRoomId}")
    public ApiResponse<List<ChatRoomDto>> getChatRoomChats(@MemberId Long memberId, @PathVariable Long chatRoomId) {
        return ApiResponse.success(chatService.getChatRoomDtosBy(memberId, chatRoomId));
    }
}
