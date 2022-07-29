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
public class Followers {

    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Follow> followers;

    public Followers(List<Follow> followers) {
        this.followers = followers;
    }

    public void add(Follow follow) {
        if (followers.contains(follow)) {
            throw new FollowException(ErrorCode.ALREADY_FOLLOW);
        }
        followers.add(follow);
    }

    public void remove(Follow follow) {
        if (!followers.contains(follow)) {
            throw new FollowException(ErrorCode.CANNOT_UNFOLLOW);
        }
        followers.remove(follow);
    }

    public Integer getCount() {
        return followers.size();
    }
}
