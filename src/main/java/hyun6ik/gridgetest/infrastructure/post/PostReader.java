package hyun6ik.gridgetest.infrastructure.post;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.post.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {

    private final PostQueryRepository postQueryRepository;

    public Post getPostBy(Long memberId, Long postId) {
        return postQueryRepository.findByIdAndMemberId(memberId, postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
    }

    public Post getPostBy(Long postId) {
        return postQueryRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
    }
}
