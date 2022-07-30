package hyun6ik.gridgetest.domain.comment.like;

import hyun6ik.gridgetest.global.error.exception.CannotUnlikeException;
import hyun6ik.gridgetest.global.error.exception.DuplicatedLikeException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
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
public class CommentLikes {

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentLike> commentLikes;

    public CommentLikes(List<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public void add(CommentLike commentLike) {
        if (commentLikes.contains(commentLike)) {
            throw new DuplicatedLikeException(ErrorCode.DUPLICATED_LIKE);
        }
        commentLikes.add(commentLike);
    }

    public void remove(CommentLike commentLike) {
        if (!commentLikes.contains(commentLike)) {
            throw new CannotUnlikeException(ErrorCode.CANNOT_UNLIKE);
        }
        commentLikes.remove(commentLike);
    }

    public Integer getCounts() {
        return commentLikes.size();
    }
}
