package hyun6ik.gridgetest.interfaces.login.controller;

import hyun6ik.gridgetest.domain.login.feign.kakao.KakaoAuthClient;
import hyun6ik.gridgetest.domain.login.service.kakao.KakaoProperties;
import hyun6ik.gridgetest.interfaces.login.dto.response.KakaoTokenDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.client.grant-type}")
    private String grantType;

    private final KakaoAuthClient kakaoAuthClient;

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @Operation(summary = "카카오 토큰을 받는 API")
    @ResponseBody
    @GetMapping("/auth/kakao/callback")
    public ResponseEntity<KakaoTokenDto> loginCallback(String code){
        MultiValueMap<String, String> userInfo = makeUserInfo(code);
        final var kakaoTokenResponseDto = kakaoAuthClient.requestAccessToken(userInfo);
        return ResponseEntity.ok(kakaoTokenResponseDto);
    }

    private MultiValueMap<String, String> makeUserInfo(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(KakaoProperties.GRANT_TYPE, grantType);
        map.add(KakaoProperties.CLIENT_ID, clientId);
        map.add(KakaoProperties.REDIRECT_URL, redirectUrl);
        map.add(KakaoProperties.CODE, code);
        map.add(KakaoProperties.CLIENT_SECRET, clientSecret);
        return map;
    }
}
