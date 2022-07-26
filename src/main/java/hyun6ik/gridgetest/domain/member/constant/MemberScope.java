package hyun6ik.gridgetest.domain.member.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberScope {

    PRIVATE("비공개계정"), PUBLIC("공개계정");

    private final String description;

}
