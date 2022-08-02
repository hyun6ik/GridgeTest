package hyun6ik.gridgetest.domain.member.entity;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import hyun6ik.gridgetest.domain.member.follow.Follow;
import hyun6ik.gridgetest.domain.member.follow.Followers;
import hyun6ik.gridgetest.domain.member.follow.Followings;
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

    @Embedded
    private Followers followers;

    @Embedded
    private Followings followings;

    @Builder
    public Member(Profile profile, String phoneNumber, String password, LocalDate birthDay, MemberStatus memberStatus) {
        this.profile = profile;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birthDay = birthDay;
        this.memberStatus = memberStatus;
        this.posts = new Posts(new ArrayList<>());
        this.followers = new Followers(new ArrayList<>());
        this.followings = new Followings(new ArrayList<>());
    }

    public void addToken(MemberToken memberToken) {
        this.memberToken = memberToken;
    }

    public String getName() {
        return this.getProfile().getName();
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

    public Integer getFollowerCount() {
        return followers.getCount();
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

    public void changePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public void updatePrivateAccount() {
        this.memberStatus.changePrivateAccount();
    }

    public void resignMember() {
        this.memberStatus.resignMember();
    }

    public Follow follow(Member toMember) {
        final Follow follow = new Follow(this, toMember);
        this.followings.add(follow);
        toMember.followers.add(follow);
        return follow;
    }

    public void unfollow(Member toMember) {
        final Follow follow = new Follow(this, toMember);
        this.followings.remove(follow);
        toMember.followers.remove(follow);
    }

    public void updateWebSite(String websiteUrl) {
        this.profile.updateWebSite(websiteUrl);
    }

    public void updateIntroduce(String introduce) {
        this.profile.updateIntroduce(introduce);
    }

    public void updateProfileImage(String image) {
        this.profile.updateImage(image);
    }

    public void updateProfileName(String name) {
        this.profile.updateName(name);
    }

    public void updateProfileNickName(String nickName) {
        this.profile.updateNickName(nickName);
    }
}
