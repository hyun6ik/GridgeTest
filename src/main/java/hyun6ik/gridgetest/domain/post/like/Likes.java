package hyun6ik.gridgetest.domain.post.like;

import hyun6ik.gridgetest.global.error.exception.CannotException;
import hyun6ik.gridgetest.global.error.exception.DuplicatedException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    public Likes(List<Like> likes) {
        this.likes = likes;
    }

    public void add(Like like) {
        if (likes.contains(like)) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_LIKE);
        }
        likes.add(like);
    }

    public Integer getCounts() {
        return likes.size();
    }

    public void remove(Like like) {
        if (!likes.contains(like)) {
            throw new CannotException(ErrorCode.CANNOT_UNLIKE);
        }
        likes.remove(like);
    }
}
