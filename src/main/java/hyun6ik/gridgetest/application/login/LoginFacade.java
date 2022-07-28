package hyun6ik.gridgetest.application.login;

import hyun6ik.gridgetest.domain.jwt.service.TokenManager;
import hyun6ik.gridgetest.domain.login.service.LoginService;
import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.entity.MemberToken;
import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.interfaces.login.dto.RegisterDto;
import hyun6ik.gridgetest.interfaces.login.dto.SocialLoginDto;
import hyun6ik.gridgetest.interfaces.login.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginFacade {

    private final LoginService loginService;
    private final MemberService memberService;
    private final TokenManager tokenManager;

    @Transactional
    public RegisterDto.Response socialLogin(String accessToken, SocialLoginDto request) {
        final SocialUserInfo socialUserInfo = loginService.getSocialUserInfo(accessToken, request.getMemberType());
        final Member member = memberService.getSocialMemberBy(socialUserInfo);
        return createToken(member);
    }

    @Transactional
    public RegisterDto.Response register(Member initMember) {
        final Member member = memberService.registerMember(initMember);
        return createToken(member);
    }

    private RegisterDto.Response createToken(Member member) {
        final TokenDto tokenDto = tokenManager.createTokenDto(member.getProfile().getNickName(), member.getMemberStatus().getMemberRole());
        member.addToken(MemberToken.of(tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpireTime()));
        return RegisterDto.Response.of(tokenDto);
    }
}
