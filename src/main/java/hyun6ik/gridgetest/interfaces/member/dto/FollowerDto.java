package hyun6ik.gridgetest.interfaces.member.dto;

import hyun6ik.gridgetest.domain.member.follow.constant.FollowStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowerDto {

    private static final String APPROVED = "팔로우 되었습니다.";
    private static final String PENDING = "팔로우 요청이 완료되었습니다.";

    private String message;

    public FollowerDto(FollowStatus followStatus) {
        this.message = followStatus == FollowStatus.APPROVED ? APPROVED : PENDING;
    }
}
