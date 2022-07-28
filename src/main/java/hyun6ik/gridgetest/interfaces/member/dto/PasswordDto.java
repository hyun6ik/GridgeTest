package hyun6ik.gridgetest.interfaces.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordDto {

    @NotBlank(message = "핸드폰번호는 필수입니다.")
    private String phoneNumber;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "비밀번호확인을 입력해주세요.")
    private String password2;
}
