package hyun6ik.gridgetest.interfaces.comment.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentRequestDto {
    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;
}
