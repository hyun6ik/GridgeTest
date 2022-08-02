package hyun6ik.gridgetest.interfaces.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChatMessageRequestDto {

    @NotNull(message = "채팅할 상대를 골라주세요.")
    private Long toId;
    @Size(max = 200, message = "메시지는 최대 200자까지 가능합니다.")
    @NotBlank(message = "메시지를 입력해주세요.")
    private String message;
}
