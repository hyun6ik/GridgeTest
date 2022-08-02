package hyun6ik.gridgetest.interfaces.login.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDto {

    private Long memberId;

    public RegisterResponseDto(Long memberId) {
        this.memberId = memberId;
    }
}
