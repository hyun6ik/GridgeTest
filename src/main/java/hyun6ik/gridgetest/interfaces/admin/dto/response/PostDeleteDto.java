package hyun6ik.gridgetest.interfaces.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteDto {

    private Long postId;
    private String message;

    public PostDeleteDto(Long postId) {
        this.postId = postId;
        this.message = "(관리자) 게시글이 삭제되었습니다.";
    }
}
