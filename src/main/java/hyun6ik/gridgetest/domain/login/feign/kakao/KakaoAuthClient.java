package hyun6ik.gridgetest.domain.login.feign.kakao;

import hyun6ik.gridgetest.interfaces.login.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com")
public interface KakaoAuthClient {

    @PostMapping("/oauth/token")
    KakaoTokenDto requestAccessToken(@RequestBody MultiValueMap<String, String> kakaoUserInfo);
}
