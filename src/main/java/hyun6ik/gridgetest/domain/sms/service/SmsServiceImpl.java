package hyun6ik.gridgetest.domain.sms.service;

import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.SmsException;
import hyun6ik.gridgetest.infrastructure.sms.SmsFactory;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService{

    @Value("${sms.key}")
    private String apiKey;
    @Value("${sms.secret}")
    private String secret;
    @Value("${sms.to_phone_number}")
    private String toPhoneNumber;

    private final SmsFactory smsFactory;


    public void sendSms(String fromPhoneNumber) {

        final var sms = new Message(apiKey, secret);
        final var params = new HashMap<String, String>();

        final var randomCode = makeRandomCode();

        params.put("to", toPhoneNumber);
        params.put("from", fromPhoneNumber);
        params.put("type", "SMS");
        params.put("text", "인증 코드는 [" + randomCode + "] 입니다. )");

        try {
            sms.send(params);
        } catch (CoolsmsException e) {
            throw new SmsException(ErrorCode.SMS_NOT_SEND);
        }

        smsFactory.createSmsCertification(fromPhoneNumber, randomCode);
    }

    private String makeRandomCode() {
        Random rand = new Random();
        StringBuilder numStr = new StringBuilder();
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr.append(ran);
        }
        return numStr.toString();
    }

    public void verifySms(String fromPhoneNumber, String randomCode) {
        if (!isVerify(fromPhoneNumber, randomCode)) {
            throw new SmsException(ErrorCode.SMS_NOT_VALID);
        }
        smsFactory.removeSmsCertification(randomCode);
    }

    private boolean isVerify(String fromPhoneNumber, String randomCode) {
        return smsFactory.hasKey(fromPhoneNumber) && smsFactory.getSmsCertification(fromPhoneNumber).equals(randomCode);
    }
}
