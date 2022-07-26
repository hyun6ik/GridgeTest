package hyun6ik.gridgetest.infrastructure.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class SmsFactory {

    private static final String PREFIX = "sms:";
    private static final int LIMIT_TIME = 3 * 60;

    private final StringRedisTemplate stringRedisTemplate;

    public void createSmsCertification(String phone, String certificationNumber) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + phone, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getSmsCertification(String phone) {
        return stringRedisTemplate.opsForValue().get(PREFIX + phone);
    }

    public void removeSmsCertification(String phone) {
        stringRedisTemplate.delete(PREFIX + phone);
    }

    public boolean hasKey(String phone) {  //(6)
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(PREFIX + phone));
    }

}