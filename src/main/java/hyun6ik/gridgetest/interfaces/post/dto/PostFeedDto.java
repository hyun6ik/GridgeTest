package hyun6ik.gridgetest.interfaces.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostFeedDto {

    private List<String> images;
    private String nickName;
    private String profileImage;
    private Integer likeCount;
    private Integer commentCount;

    @QueryProjection
    public PostFeedDto(String nickName, String profileImage, Integer likeCount, Integer commentCount) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public void addImages(List<String> images) {
        this.images = images;
    }
}
