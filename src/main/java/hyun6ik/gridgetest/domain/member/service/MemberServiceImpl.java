package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.LoginException;
import hyun6ik.gridgetest.infrastructure.member.MemberReader;
import hyun6ik.gridgetest.infrastructure.member.MemberStore;
import hyun6ik.gridgetest.infrastructure.member.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberReader memberReader;
    private final MemberStore memberStore;
    private final MemberValidator memberValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member registerMember(Member initMember) {
        memberValidator.validateRegister(initMember);
        initMember.encodePassword(passwordEncoder);
        return memberStore.store(initMember);
    }

    @Override
    public Member getSocialMemberBy(SocialUserInfo socialUserInfo) {
        return memberReader.findSocialMemberBy(socialUserInfo)
                .orElseThrow(() -> new LoginException(ErrorCode.NOT_FOUND_SOCIAL_USER));
    }

    @Override
    public Member getMemberBy(Long memberId) {
        return memberReader.getMemberBy(memberId);
    }

    @Override
    public void validateRefreshTokenExpirationTime(LocalDateTime tokenExpirationTime, LocalDateTime now) {
        memberValidator.validateRefreshToken(tokenExpirationTime, now);
    }
}
