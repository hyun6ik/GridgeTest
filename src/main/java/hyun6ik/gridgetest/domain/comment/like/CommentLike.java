package hyun6ik.gridgetest.domain.comment.like;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public CommentLike(Comment comment, Member member) {
        this.comment = comment;
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentLike commentLike = (CommentLike) o;
        return Objects.equals(comment, commentLike.getComment()) &&
                Objects.equals(member, commentLike.getMember());
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, member);
    }
}
