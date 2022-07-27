package hyun6ik.gridgetest.domain.login.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;

public interface SocialLoginService {

    boolean support(String memberType);
    SocialUserInfo getUserInfo(String accessToken);
}
