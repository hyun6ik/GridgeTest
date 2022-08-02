package hyun6ik.gridgetest.interfaces.login.controller;

import hyun6ik.gridgetest.application.login.LoginFacade;
import hyun6ik.gridgetest.domain.sms.constant.SmsConstraints;
import hyun6ik.gridgetest.domain.sms.service.SmsService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.common.ApiResponse;
import hyun6ik.gridgetest.interfaces.login.constant.LoginConstraints;
import hyun6ik.gridgetest.interfaces.login.dto.request.*;
import hyun6ik.gridgetest.interfaces.login.dto.response.LoginResponseDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.RegisterResponseDto;
import hyun6ik.gridgetest.interfaces.login.dto.response.TokenAccessDto;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "SMS 인증 문자를 보내는 API")
    @PostMapping("/sms/send")
    public ApiResponse<String> sendSms(@Valid @RequestBody PhoneNumberRequestDto request) {
        smsService.sendSms(request.getFromPhoneNumber());
        return ApiResponse.success(SmsConstraints.SEND_SUCCESS);
    }

    @Operation(summary = "SMS 인증 문자를 검증는 API")
    @PostMapping("/sms/verify")
    public ApiResponse<String> verifySms(@Valid @RequestBody PhoneNumberVerifyDto request) {
        smsService.verifySms(request.getFromPhoneNumber(), request.getRandomCode());
        return ApiResponse.success(SmsConstraints.VERIFY_SUCCESS);
    }

    @Operation(summary = "소셜 로그인 하는 API")
    @PostMapping("/oauth/login")
    public ApiResponse<LoginResponseDto> socialLogin(@RequestHeader("Authorization") String accessToken, @Valid @RequestBody SocialLoginDto request) {
        return ApiResponse.success(loginFacade.socialLogin(accessToken, request));
    }

    @Operation(summary = "일반 로그인하는 API")
    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ApiResponse.success(loginFacade.login(request.getNickName(), request.getPassword()));
    }

    @Operation(summary = "회원가입 API")
    @PostMapping("/new")
    public ApiResponse<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        return ApiResponse.success(loginFacade.register(request.toEntity()));
    }

    @Operation(summary = "토큰을 재발급 받는 API")
    @LoginUser
    @PostMapping("/refresh-token")
    public ApiResponse<TokenAccessDto> refreshAccessToken(@MemberId Long memberId) {
        return ApiResponse.success(loginFacade.createAccessTokenByRefreshToken(memberId, LocalDateTime.now()));
    }

    @Operation(summary = "로그아웃 하는 API")
    @LoginUser
    @PostMapping("/logout")
    public ApiResponse<String> logout(@MemberId Long memberId) {
        loginFacade.logout(memberId);
        return ApiResponse.success(LoginConstraints.LOGOUT_SUCCESS);
    }
}
