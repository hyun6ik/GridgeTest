package hyun6ik.gridgetest.interfaces.login.controller;

import hyun6ik.gridgetest.domain.sms.constant.SmsConstraints;
import hyun6ik.gridgetest.domain.sms.service.SmsService;
import hyun6ik.gridgetest.interfaces.login.dto.PhoneNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final SmsService smsService;

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

}
