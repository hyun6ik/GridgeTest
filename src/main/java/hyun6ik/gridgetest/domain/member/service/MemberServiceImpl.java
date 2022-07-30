package hyun6ik.gridgetest.domain.member.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.infrastructure.member.MemberReader;
import hyun6ik.gridgetest.infrastructure.member.MemberStore;
import hyun6ik.gridgetest.infrastructure.member.MemberValidator;
import hyun6ik.gridgetest.interfaces.member.dto.FollowDto;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


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

    @Override
    @Transactional
    public void resignMember(Long memberId) {
        final Member member = memberReader.getMemberBy(memberId);
        member.resignMember();
    }

    @Override
    @Transactional
    public FollowDto followMember(Long fromId, Long toId) {
        final Member fromMember = memberReader.getMemberBy(fromId);
        final Member toMember = memberReader.getMemberBy(toId);
        fromMember.follow(toMember);
        return new FollowDto(toMember.getFollowerCount(), true);
    }

    @Override
    @Transactional
    public FollowDto unfollowMember(Long fromId, Long toId) {
        final Member fromMember = memberReader.getMemberBy(fromId);
        final Member toMember = memberReader.getMemberBy(toId);
        fromMember.unfollow(toMember);
        return new FollowDto(toMember.getFollowerCount(), false);

    }

    @Override
    public MyPageDto getMyPageDtoBy(Long memberId, Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 9);
        return memberReader.getMyPageDtoBy(memberId, pageable);
    }

    @Override
    @Transactional
    public void updateProfileWebsite(Long memberId, String websiteUrl) {
        final Member member = memberReader.getMemberBy(memberId);
        member.updateWebSite(websiteUrl);
    }

    @Override
    @Transactional
    public void updateProfileIntroduce(Long memberId, String introduce) {
        final Member member = memberReader.getMemberBy(memberId);
        member.updateIntroduce(introduce);
    }

    @Override
    @Transactional
    public void updateProfileImage(Long memberId, String image) {
        final Member member = memberReader.getMemberBy(memberId);
        member.updateProfileImage(image);
    }
}
