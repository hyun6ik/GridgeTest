package hyun6ik.gridgetest.interfaces.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowDto {

    private Integer followCount;
    private Boolean isFollowing;

    public FollowDto(Integer followCount, Boolean isFollowing) {
        this.followCount = followCount;
        this.isFollowing = isFollowing;
    }
}
