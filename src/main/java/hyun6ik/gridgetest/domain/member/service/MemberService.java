package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;

public interface MemberService {

    Member registerMember(Member initMember);

    Member getSocialMemberBy(SocialUserInfo socialUserInfo);

    Member getMemberBy(Long memberId);
}
