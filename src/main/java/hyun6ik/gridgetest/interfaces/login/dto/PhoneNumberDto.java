package hyun6ik.gridgetest.interfaces.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberDto {

    @Getter
    @Setter
    public static class Request {
        private String fromPhoneNumber;
    }

    @Getter
    @Setter
    public static class Verify {
        private String fromPhoneNumber;
        private String randomCode;
    }
}