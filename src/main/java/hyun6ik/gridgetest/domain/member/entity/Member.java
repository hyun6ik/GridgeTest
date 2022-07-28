package hyun6ik.gridgetest.domain.member.entity;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import hyun6ik.gridgetest.domain.post.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Profile profile;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    private String password;

    private LocalDate birthDay;

    @Embedded
    private MemberStatus memberStatus;

    @Embedded
    private MemberToken memberToken;

    @Embedded
    private Posts posts;

    @Builder
    public Member(Profile profile, String phoneNumber, String password, LocalDate birthDay, MemberStatus memberStatus) {
        this.profile = profile;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birthDay = birthDay;
        this.memberStatus = memberStatus;
        this.posts = new Posts(new ArrayList<>());
    }

    public void addToken(MemberToken memberToken) {
        this.memberToken = memberToken;
    }

    public String getNickName() {
        return this.getProfile().getNickName();
    }

    public MemberRole getMemberRole() {
        return this.getMemberStatus().getMemberRole();
    }

    public LocalDateTime getTokenExpirationTime() {
        return this.getMemberToken().getTokenExpirationTime();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        if (!this.memberStatus.getMemberType().equals(MemberType.GENERAL)) {
            return;
        }
        this.password = passwordEncoder.encode(password);
    }

    public void removeRefreshToken() {
        this.memberToken.removeRefreshToken();;
    }
}
