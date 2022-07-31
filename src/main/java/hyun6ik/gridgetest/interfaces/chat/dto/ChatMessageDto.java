package hyun6ik.gridgetest.interfaces.chat.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChatMessageDto {

    @Getter
    @Setter
    public static class Request {
        @NotNull(message = "채팅할 상대를 골라주세요.")
        private Long toId;
        @Size(max = 200, message = "메시지는 최대 200자까지 가능합니다.")
        @NotBlank(message = "메시지를 입력해주세요.")
        private String message;
    }

    @Getter
    @Setter
    public static class Response {
        private Long chatRoomId;
        private String message;

        public Response(Long chatRoomId, String message) {
            this.chatRoomId = chatRoomId;
            this.message = message;
        }
    }


}
