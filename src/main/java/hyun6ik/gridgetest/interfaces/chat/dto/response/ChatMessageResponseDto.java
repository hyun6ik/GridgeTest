package hyun6ik.gridgetest.interfaces.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageResponseDto {

    private Long chatRoomId;
    private String message;

    public ChatMessageResponseDto(Long chatRoomId, String message) {
        this.chatRoomId = chatRoomId;
        this.message = message;
    }
}
