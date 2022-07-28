package hyun6ik.gridgetest.infrastructure.post;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.global.error.exception.BusinessException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostValidator {

    public void validatePost(Post initPost) {
        if (initPost.getImages().getImageUrls().size() == 0) {
            throw new BusinessException(ErrorCode.NEED_AT_LEAST_ONE_IMAGE);
        }
    }
}
