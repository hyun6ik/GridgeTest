package hyun6ik.gridgetest.infrastructure.member;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.LoginException;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateRegister(Member initMember) {
        passwordCheck(initMember.getPassword());
        duplicateNickName(initMember.getNickName());
        nickNameRegexCheck(initMember.getNickName());
    }

    private void passwordCheck(String password) {
        if (password == null) {
            return;
        }
        String regex = "[!@#$%^&*(),.?\\\":{}|<>]";
        if (Pattern.matches(regex, password)) {
            throw new LoginException(ErrorCode.REGEX_CHECK_PASSWORD);
        }
    }

    private void nickNameRegexCheck(String nickName) {
        String regex = "[^0-9a-z_.]";
        if (Pattern.matches(regex, nickName)) {
            throw new LoginException(ErrorCode.REGEX_CHECK_NICKNAME);
        }
    }

    private void duplicateNickName(String nickName) {
        if (memberRepository.findByProfile_NickName(nickName).isPresent()) {
            throw new LoginException(String.format("사용자 이름 %s", nickName), ErrorCode.DUPLICATED_MEMBER);
        }
    }
}