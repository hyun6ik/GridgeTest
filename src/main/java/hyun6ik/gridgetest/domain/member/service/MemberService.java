package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;

import java.time.LocalDateTime;

public interface MemberService {

    Member registerMember(Member initMember);

    Member getSocialMemberBy(SocialUserInfo socialUserInfo);

    Member getMemberBy(Long memberId);

    void validateRefreshTokenExpirationTime(LocalDateTime tokenExpirationTime, LocalDateTime now);

    Member login(String nickName, String password);

    void changePassword(String memberId, String password, String password2);

    void updatePrivateAccount(Long memberId);
}
