package hyun6ik.gridgetest.domain.login.service;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;

public interface LoginService {

    SocialUserInfo getSocialUserInfo(String accessToken, String memberType);
}
