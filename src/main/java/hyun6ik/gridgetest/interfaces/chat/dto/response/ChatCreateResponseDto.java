package hyun6ik.gridgetest.interfaces.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatCreateResponseDto {

    private Long chatRoomId;

    public ChatCreateResponseDto(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
}
