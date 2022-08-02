package hyun6ik.gridgetest.application.login;

import hyun6ik.gridgetest.domain.jwt.service.TokenManager;
import hyun6ik.gridgetest.domain.login.service.LoginService;
import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.entity.MemberToken;
import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.interfaces.login.dto.request.SocialLoginDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.LoginResponseDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.RegisterResponseDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.TokenAccessDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginFacade {

    private final LoginService loginService;
    private final MemberService memberService;
    private final TokenManager tokenManager;

    @Transactional
    public LoginResponseDto socialLogin(String accessToken, SocialLoginDto request) {
        final SocialUserInfo socialUserInfo = loginService.getSocialUserInfo(accessToken, request.getMemberType());
        final Member member = memberService.getSocialMemberBy(socialUserInfo);
        return createToken(member);
    }

    @Transactional
    public RegisterResponseDto register(Member initMember) {
        final Member member = memberService.registerMember(initMember);
        return new RegisterResponseDto(member.getId());
    }

    private LoginResponseDto createToken(Member member) {
        final TokenDto tokenDto = tokenManager.createTokenDto(member.getId(), member.getMemberRole());
        member.addToken(MemberToken.of(tokenDto.getRefreshToken(), tokenDto.getRefreshTokenExpireTime()));
        return LoginResponseDto.of(tokenDto);
    }

    public TokenAccessDto createAccessTokenByRefreshToken(Long memberId, LocalDateTime now) {
        final Member member = memberService.getMemberBy(memberId);
        memberService.validateRefreshTokenExpirationTime(member.getTokenExpirationTime(), now);

        final Date newAccessTokenExpireTime = tokenManager.createAccessTokenExpireTime();
        final String newAccessToken = tokenManager.createAccessToken(memberId, member.getMemberRole(), newAccessTokenExpireTime);

        return TokenAccessDto.of(newAccessToken, newAccessTokenExpireTime);
    }

    @Transactional
    public void logout(Long memberId) {
        final Member member = memberService.getMemberBy(memberId);
        member.removeRefreshToken();
    }

    public LoginResponseDto login(String nickName, String password) {
        final Member member = memberService.login(nickName, password);
        return createToken(member);
    }
}
