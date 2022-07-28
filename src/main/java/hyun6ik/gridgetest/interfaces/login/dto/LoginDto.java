package hyun6ik.gridgetest.interfaces.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class LoginDto {

    @Getter
    @Setter
    public static class Request {
        private String nickName;
        private String password;
    }

    @Getter
    @Setter
    public static class Response {

        private String grantType;
        private String accessToken;
        private Date accessTokenExpireTime;
        private String refreshToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        @Builder
        public Response(String grantType, String accessToken, Date accessTokenExpireTime, String refreshToken, Date refreshTokenExpireTime) {
            this.grantType = grantType;
            this.accessToken = accessToken;
            this.accessTokenExpireTime = accessTokenExpireTime;
            this.refreshToken = refreshToken;
            this.refreshTokenExpireTime = refreshTokenExpireTime;
        }

        public static LoginDto.Response of(TokenDto tokenDto) {
            return LoginDto.Response.builder()
                    .grantType(tokenDto.getGrantType())
                    .accessToken(tokenDto.getAccessToken())
                    .accessTokenExpireTime(tokenDto.getAccessTokenExpireTime())
                    .refreshToken(tokenDto.getRefreshToken())
                    .refreshTokenExpireTime(tokenDto.getRefreshTokenExpireTime())
                    .build();
        }
    }
}
