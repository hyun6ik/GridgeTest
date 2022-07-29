package hyun6ik.gridgetest.domain.post.image;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private Boolean isRepImage;

    public Image(String url, Boolean isRepImage) {
        this.url = url;
        this.isRepImage = isRepImage;
    }

    public void belongTo(Post post) {
        this.post = post;
    }

}
