package hyun6ik.gridgetest.interfaces.login.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hyun6ik.gridgetest.domain.jwt.constant.GrantType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenAccessDto {

    private String grantType;

    private String accessToken;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date accessTokenExpireTime;

    @Builder
    public TokenAccessDto(String grantType, String accessToken, Date accessTokenExpireTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static TokenAccessDto of(String newAccessToken, Date newAccessTokenExpireTime) {
        return TokenAccessDto.builder()
                .grantType(GrantType.BEARRER.getType())
                .accessToken(newAccessToken)
                .accessTokenExpireTime(newAccessTokenExpireTime)
                .build();
    }
}
