package hyun6ik.gridgetest.interfaces.login.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoginResponseDto {

    private String grantType;
    private String accessToken;
    private Date accessTokenExpireTime;
    private String refreshToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date refreshTokenExpireTime;

    @Builder
    public LoginResponseDto(String grantType, String accessToken, Date accessTokenExpireTime, String refreshToken, Date refreshTokenExpireTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    public static LoginResponseDto of(TokenDto tokenDto) {
        return LoginResponseDto.builder()
                .grantType(tokenDto.getGrantType())
                .accessToken(tokenDto.getAccessToken())
                .accessTokenExpireTime(tokenDto.getAccessTokenExpireTime())
                .refreshToken(tokenDto.getRefreshToken())
                .refreshTokenExpireTime(tokenDto.getRefreshTokenExpireTime())
                .build();
    }

}
