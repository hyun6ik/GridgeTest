package hyun6ik.gridgetest.domain.member.follow;

import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.FollowException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Followings {

    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Follow> followings;

    public Followings(List<Follow> followings) {
        this.followings = followings;
    }

    public void add(Follow follow) {
        if (followings.contains(follow)) {
            throw new FollowException(ErrorCode.ALREADY_FOLLOWING);
        }
        followings.add(follow);
    }

    public void remove(Follow follow) {
        if (!followings.contains(follow)) {
            throw new FollowException(ErrorCode.CANNOT_UNFOLLOW);
        }
        followings.remove(follow);
    }
}
