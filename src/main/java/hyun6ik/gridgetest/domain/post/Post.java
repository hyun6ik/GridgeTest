package hyun6ik.gridgetest.domain.post;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.comment.entity.Comments;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.content.PostContent;
import hyun6ik.gridgetest.domain.post.image.Images;
import hyun6ik.gridgetest.domain.post.like.Likes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Images images;

    @Embedded
    private PostContent postContent;

    @Embedded
    private Likes likes;

    @Embedded
    private Comments comments;

    @Builder
    public Post(Member member, Images images, PostContent postContent, Likes likes, Comments comments) {
        this.member = member;
        this.images = images;
        this.postContent = postContent;
        this.likes = likes;
        this.comments = comments;

        images.belongTo(this);
    }
}
