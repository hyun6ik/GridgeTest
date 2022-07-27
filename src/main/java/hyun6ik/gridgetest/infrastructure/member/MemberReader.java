package hyun6ik.gridgetest.infrastructure.member;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberQueryRepository;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberReader {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public Optional<Member> getMemberBy(String nickName) {
        return memberRepository.findByProfile_NickName(nickName);
    }

    public Optional<Member> findSocialMemberBy(SocialUserInfo socialUserInfo) {
        return memberQueryRepository.findSocialMemberBy(socialUserInfo);
    }
}
