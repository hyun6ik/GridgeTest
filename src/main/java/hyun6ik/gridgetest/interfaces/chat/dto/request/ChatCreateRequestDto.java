package hyun6ik.gridgetest.interfaces.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChatCreateRequestDto {
    @NotNull(message = "채팅할 상대를 골라주세요.")
    private Long guestId;
}
