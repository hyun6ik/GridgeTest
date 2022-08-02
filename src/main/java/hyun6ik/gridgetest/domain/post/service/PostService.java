package hyun6ik.gridgetest.domain.post.service;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostFeedDto;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostRegisterResponseDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    PostRegisterResponseDto createPost(Post initPost);

    void deletePost(Long memberId, Long postId);

    void updatePost(Long memberId, Long postId, String content);

    Post getPostBy(Long postId);

    PostFeedDto getPostFeedDtoBy(Long postId);

    Page<PostFeedDto> getHomeFeedDtosBy(Long memberId, Optional<Integer> page);
}
