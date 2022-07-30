package hyun6ik.gridgetest.infrastructure.post;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.post.repository.PostQueryRepository;
import hyun6ik.gridgetest.interfaces.post.dto.PostFeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PostFeedDto getPostFeedDtoBy(Long postId) {
        final PostFeedDto postFeedDto = postQueryRepository.findPostFeedDtoBy(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
        final List<String> images = postQueryRepository.findPostFeedDtoImagesBy(postId);
        postFeedDto.addImages(images);
        return postFeedDto;
    }

    public Page<PostFeedDto> getHomeFeedDtosBy(Long memberId, Pageable pageable) {
        final Page<PostFeedDto> homeFeedDtos = postQueryRepository.findHomeFeedDtosBy(memberId, pageable);
        homeFeedDtos.forEach(homeFeedDto -> homeFeedDto.addImages(postQueryRepository.findPostFeedDtoImagesBy(homeFeedDto.getPostId())));
        return homeFeedDtos;
    }
}
