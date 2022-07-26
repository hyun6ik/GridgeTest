package hyun6ik.gridgetest.domain.login.feign.kakao;

import hyun6ik.gridgetest.domain.login.service.kakao.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoClient", url = "https://kapi.kakao.com")
public interface KakaoClient {

    @GetMapping("/v2/user/me")
    KakaoUserInfo requestKakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}
