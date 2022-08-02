package hyun6ik.gridgetest.interfaces.login.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberVerifyDto {
    private String fromPhoneNumber;
    private String randomCode;
}
