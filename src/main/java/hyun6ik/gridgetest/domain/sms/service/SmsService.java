package hyun6ik.gridgetest.domain.sms.service;

public interface SmsService {

    void sendSms(String fromPhoneNumber);

    void verifySms(String fromPhoneNumber, String randomCode);
}
