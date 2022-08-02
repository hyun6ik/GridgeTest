package hyun6ik.gridgetest.interfaces.post.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRegisterResponseDto {
    private Long postId;

    public PostRegisterResponseDto(Long postId) {
        this.postId = postId;
    }
}
