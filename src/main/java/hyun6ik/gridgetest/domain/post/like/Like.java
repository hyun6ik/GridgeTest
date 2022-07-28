package hyun6ik.gridgetest.domain.post.like;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "LIKES")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Like(Post post, Member member) {
        this.post = post;
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
        Like like = (Like) o;
        return Objects.equals(post, like.getPost()) &&
                Objects.equals(member, like.getMember());
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, member);
    }
}
