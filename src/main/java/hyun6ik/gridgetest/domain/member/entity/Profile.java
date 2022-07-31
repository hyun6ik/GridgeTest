package hyun6ik.gridgetest.domain.member.entity;

import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.ProfileException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.Period;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String nickName;

    private String image;

    private String webSite;

    private String introduce;

    private Integer nameCount;

    private Integer nickNameCount;

    private LocalDate nameUpdateAt;

    private LocalDate nickNameUpdateAt;

    @Builder
    public Profile(String name, String nickName, String image, String webSite, String introduce) {
        this.name = name;
        this.nickName = nickName;
        this.image = image;
        this.webSite = webSite;
        this.introduce = introduce;
        this.nameCount = 0;
        this.nickNameCount = 0;
    }

    public void updateWebSite(String website) {
        this.webSite = website;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void updateImage(String image) {
        this.image = image;
    }

    public void updateName(String name) {
        if (nameUpdateAt == null) {
            this.name = name;
            this.nameCount++;
            this.nameUpdateAt = LocalDate.now();
            return;
        }
        if (withinFourteenDays(nameUpdateAt, LocalDate.now())) {
            validateCount(nameCount);
            this.name = name;
            this.nameCount++;
            return;
        }
        this.name = name;
        this.nameCount = 1;
        this.nameUpdateAt = LocalDate.now();
    }

    public void updateNickName(String nickName) {
        if (nickNameUpdateAt == null) {
            this.nickName = nickName;
            this.nickNameCount++;
            this.nickNameUpdateAt = LocalDate.now();
            return;
        }
        if (withinFourteenDays(nickNameUpdateAt, LocalDate.now())) {
            validateCount(nickNameCount);
            this.nickName = nickName;
            this.nickNameCount++;
            return;
        }
        this.nickName = nickName;
        this.nickNameCount = 1;
        this.nickNameUpdateAt = LocalDate.now();
    }

    private void validateCount(Integer nameCount) {
        if (nameCount == 2) {
            throw new ProfileException(ErrorCode.PROFILE_NAME_COUNT);
        }
    }

    private boolean withinFourteenDays(LocalDate updateAt, LocalDate now) {
        final int days = Period.between(updateAt, now).getDays();
        return days <= 14;
    }
}
