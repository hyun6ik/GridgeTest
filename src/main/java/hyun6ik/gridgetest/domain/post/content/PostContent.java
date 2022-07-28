package hyun6ik.gridgetest.domain.post.content;

import hyun6ik.gridgetest.global.error.exception.BusinessException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostContent {

    public static final int MAXIMUM_CONTENT_LENGTH = 500;

    @Lob
    private String content;

    public PostContent(String content) {
        validateLengthIsOverThanMaximumContentLength(content);
        this.content = content;
    }

    private void validateLengthIsOverThanMaximumContentLength(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) {
            throw new BusinessException(ErrorCode.MAXIMUM_CONTENT_LENGTH);
        }
    }
}
