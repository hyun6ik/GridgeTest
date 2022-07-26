package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.interfaces.member.dto.FollowerDto;
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

    FollowerDto followMember(Long fromId, Long toId);

    void unfollowMember(Long fromId, Long toId);

    void privateApproveFollowMember(Long followId);

    MyPageDto getMyPageDtoBy(Long memberId, Optional<Integer> page);

    void updateProfileWebsite(Long memberId, String websiteUrl);

    void updateProfileIntroduce(Long memberId, String introduce);

    void updateProfileImage(Long memberId, String image);

    void updateProfileName(Long memberId, String name);

    void updateProfileNickName(Long memberId, String nickName);

    void privateRejectFollowMember(Long memberId, Long followId);
}
