package hyun6ik.gridgetest.domain.login.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final List<SocialLoginService> socialLoginServices;

    public SocialUserInfo getSocialUserInfo(String accessToken, String memberType) {
        final SocialLoginService socialLoginService = routingService(memberType);
        return socialLoginService.getUserInfo(accessToken);
    }

    private SocialLoginService routingService(String memberType) {
        return socialLoginServices.stream()
                .filter(socialLoginService -> socialLoginService.support(memberType))
                .findFirst()
                .orElseThrow(() -> new LoginException(ErrorCode.INVALID_MEMBER_TYPE));
    }
}
