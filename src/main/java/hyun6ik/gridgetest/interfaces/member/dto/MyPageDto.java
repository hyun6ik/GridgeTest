package hyun6ik.gridgetest.interfaces.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Getter
@Setter
public class MyPageDto {

    private String nickName;
    private String profileImage;
    private String name;
    private Integer postCount;
    private Integer followerCount;
    private Integer followingCount;
    private Page<PostDto> postDtos;

    @QueryProjection
    public MyPageDto(String nickName, String profileImage, String name, Integer postCount, Integer followerCount, Integer followingCount) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.name = name;
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public void addPageDto(Page<PostDto> pageDtos) {
        this.postDtos = pageDtos;
    }

    @Getter
    @Setter
    public static class PostDto {
        private Long postId;
        private String mainImage;

        @QueryProjection
        public PostDto(Long postId, String mainImage) {
            this.postId = postId;
            this.mainImage = mainImage;
        }
    }

}
