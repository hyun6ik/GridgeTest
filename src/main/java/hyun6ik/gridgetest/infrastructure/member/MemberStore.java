package hyun6ik.gridgetest.infrastructure.member;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MemberStore {

    private final MemberRepository memberRepository;

    @Transactional
    public Member store(Member initMember) {
        return memberRepository.save(initMember);
    }
}
