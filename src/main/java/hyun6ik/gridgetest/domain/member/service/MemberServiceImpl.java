package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
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
        return memberReader.findSocialMemberBy(socialUserInfo);
    }

    @Override
    public Member getMemberBy(Long memberId) {
        return memberReader.getMemberBy(memberId);
    }

    @Override
    public void validateRefreshTokenExpirationTime(LocalDateTime tokenExpirationTime, LocalDateTime now) {
        memberValidator.validateRefreshToken(tokenExpirationTime, now);
    }

    @Override
    public Member login(String nickName, String password) {
        final Member member = memberReader.getMemberBy(nickName);
        memberValidator.isRightPassword(passwordEncoder, member.getPassword(), password);
        return member;
    }

    @Override
    @Transactional
    public void changePassword(String phoneNumber, String password, String password2) {
        memberValidator.passwordCheck(password, password2);
        final Member member = memberReader.getMemberByPhoneNumber(phoneNumber);
        member.changePassword(passwordEncoder, password);
    }

    @Override
    @Transactional
    public void updatePrivateAccount(Long memberId) {
        final Member member = memberReader.getMemberBy(memberId);
        member.updatePrivateAccount();
    }
}
