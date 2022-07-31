package hyun6ik.gridgetest.interfaces.chat.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class ChatCreateDto {

    @Getter
    @Setter
    public static class Request {
        @NotNull(message = "채팅할 상대를 골라주세요.")
        private Long guestId;
    }

    @Getter
    @Setter
    public static class Response {

        private Long chatRoomId;

        public Response(Long chatRoomId) {
            this.chatRoomId = chatRoomId;
        }
    }




}
