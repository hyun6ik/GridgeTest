package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.interfaces.member.dto.FollowDto;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberService {

    Member registerMember(Member initMember);

    Member getSocialMemberBy(SocialUserInfo socialUserInfo);

    Member getMemberBy(Long memberId);

    void validateRefreshTokenExpirationTime(LocalDateTime tokenExpirationTime, LocalDateTime now);

    Member login(String nickName, String password);

    void changePassword(String memberId, String password, String password2);

    void updatePrivateAccount(Long memberId);

    void resignMember(Long memberId);

    FollowDto followMember(Long fromId, Long toId);

    FollowDto unfollowMember(Long fromId, Long toId);

    MyPageDto getMyPageDtoBy(Long memberId, Optional<Integer> page);

    void updateProfileWebsite(Long memberId, String websiteUrl);

    void updateProfileIntroduce(Long memberId, String introduce);
}
