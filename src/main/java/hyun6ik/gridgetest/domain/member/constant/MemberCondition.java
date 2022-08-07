package hyun6ik.gridgetest.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberCondition {

    NORMAL("정상"), DISABLED("비활성화"), RESIGNED("탈퇴"), BLOCK("정지");

    private final String description;
}
