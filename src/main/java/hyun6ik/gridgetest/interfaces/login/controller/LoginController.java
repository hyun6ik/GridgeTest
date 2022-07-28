package hyun6ik.gridgetest.interfaces.login.controller;

import hyun6ik.gridgetest.application.login.LoginFacade;
import hyun6ik.gridgetest.domain.sms.constant.SmsConstraints;
import hyun6ik.gridgetest.domain.sms.service.SmsService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.login.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final SmsService smsService;
    private final LoginFacade loginFacade;

    @PostMapping("/sms/send")
    public ResponseEntity<String> sendSms(@Valid @RequestBody PhoneNumberDto.Request request) {
        smsService.sendSms(request.getFromPhoneNumber());
        return ResponseEntity.ok(SmsConstraints.SEND_SUCCESS);
    }

    @PostMapping("/sms/verify")
    public ResponseEntity<PhoneNumberDto.Response> verifySms(@Valid @RequestBody PhoneNumberDto.Verify request) {
        smsService.verifySms(request.getFromPhoneNumber(), request.getRandomCode());
        return ResponseEntity.ok(new PhoneNumberDto.Response(true));
    }

    @PostMapping("/oauth/login")
    public ResponseEntity<LoginDto.Response> socialLogin(@RequestHeader("Authorization") String accessToken, @RequestBody SocialLoginDto request) {
        return ResponseEntity.ok(loginFacade.socialLogin(accessToken, request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto.Response> login(@RequestBody LoginDto.Request request) {
        return ResponseEntity.ok(loginFacade.login(request));
    }

    @PostMapping("/new")
    public ResponseEntity<RegisterDto.Response> register(@RequestBody RegisterDto.Request request) {
        return ResponseEntity.ok(loginFacade.register(request.toEntity()));
    }

    @LoginUser
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenAccessDto> refreshAccessToken(@MemberId Long memberId) {
        return ResponseEntity.ok(loginFacade.createAccessTokenByRefreshToken(memberId, LocalDateTime.now()));
    }

    @LoginUser
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@MemberId Long memberId) {
        return ResponseEntity.ok(loginFacade.logout(memberId));
    }
}
