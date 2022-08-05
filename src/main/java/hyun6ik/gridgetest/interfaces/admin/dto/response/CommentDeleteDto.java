package hyun6ik.gridgetest.interfaces.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDeleteDto {

    private Long commentId;
    private String message;

    public CommentDeleteDto(Long commentId) {
        this.commentId = commentId;
        this.message = "(관리자) 댓글이 삭제되었습니다.";
    }
}
