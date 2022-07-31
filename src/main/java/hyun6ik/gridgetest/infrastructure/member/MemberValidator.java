package hyun6ik.gridgetest.infrastructure.member;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.AuthenticationException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.LoginException;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberValidator {

    final int MIN = 6;
    final int MAX = 20;

    private final MemberRepository memberRepository;

    public void validateRegister(Member initMember) {
        passwordRegexCheck(initMember.getPassword());
        duplicateNickName(initMember.getNickName());
        regexCheck(initMember.getName());
        regexCheck(initMember.getNickName());
    }

    private void passwordRegexCheck(String password) {
        if (password == null) {
            return;
        }
        String specialCharacter = "[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|]*$";
        if (Pattern.matches(specialCharacter, password)) {
            throw new LoginException(ErrorCode.REGEX_CHECK_PASSWORD);
        }

        final int length = password.length();
        if (length < MIN || length > MAX) {
            throw new LoginException(ErrorCode.LENGTH_PASSWORD);
        }
    }

    public void regexCheck(String nickName) {
        String regex = "[0-9|a-z|_.]*$";
        if (!Pattern.matches(regex, nickName)) {
            throw new LoginException(ErrorCode.REGEX_CHECK_NICKNAME);
        }
    }

    public void duplicateNickName(String nickName) {
        if (memberRepository.findByProfile_NickName(nickName).isPresent()) {
            throw new LoginException(String.format("사용자 이름 %s", nickName), ErrorCode.DUPLICATED_MEMBER);
        }
    }

    public void validateRefreshToken(LocalDateTime refreshTokenExpirationTime, LocalDateTime now) {
        if (refreshTokenExpirationTime.isBefore(now)) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
    }

    public void isRightPassword(PasswordEncoder passwordEncoder, String memberPassword, String password) {
        if (!passwordEncoder.matches(password, memberPassword)) {
            throw new LoginException(ErrorCode.MISMATCHED_PASSWORD);
        }
    }

    public void passwordCheck(String password, String password2) {
        if (!password.equals(password2)) {
            throw new LoginException(ErrorCode.MISMATCHED_PASSWORD);
        }
        passwordRegexCheck(password);
    }
}
