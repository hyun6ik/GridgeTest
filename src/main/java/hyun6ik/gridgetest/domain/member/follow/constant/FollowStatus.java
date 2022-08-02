package hyun6ik.gridgetest.domain.member.follow.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FollowStatus {

    APPROVED("승인된"), PENDING("요청중");

    private final String description;
}
