package hyun6ik.gridgetest.domain.comment.entity;

import hyun6ik.gridgetest.global.error.exception.BusinessException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentContent {

    private static final int MAX_COMMENT_CONTENT_LENGTH = 200;

    @Column(nullable = false, length = 200)
    @Lob
    private String content;

    public CommentContent(String content) {
        if (isNotValidContent(content)) {
            throw new BusinessException(ErrorCode.MAX_COMMENT_CONTENT_LENGTH);
        }
        this.content = content;
    }

    private boolean isNotValidContent(String content) {
        return Objects.isNull(content)
                || content.isBlank()
                || content.length() >= MAX_COMMENT_CONTENT_LENGTH;
    }

}
