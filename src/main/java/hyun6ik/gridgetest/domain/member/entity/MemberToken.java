package hyun6ik.gridgetest.domain.member.entity;

import hyun6ik.gridgetest.global.util.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Date;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberToken {

    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public MemberToken(String refreshToken, LocalDateTime tokenExpirationTime) {
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public static MemberToken of(String refreshToken, Date refreshTokenExpireTime) {
        return MemberToken.builder()
                .refreshToken(refreshToken)
                .tokenExpirationTime(DateTimeUtils.convertToLocalDateTime(refreshTokenExpireTime))
                .build();
    }
}
