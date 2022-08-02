package hyun6ik.gridgetest.interfaces.login.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoTokenDto {

    private String token_type;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private String scope;
}
