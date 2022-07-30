package hyun6ik.gridgetest.domain.post.service;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.infrastructure.post.PostReader;
import hyun6ik.gridgetest.infrastructure.post.PostStore;
import hyun6ik.gridgetest.infrastructure.post.PostValidator;
import hyun6ik.gridgetest.interfaces.post.dto.PostFeedDto;
import hyun6ik.gridgetest.interfaces.post.dto.PostRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostReader postReader;
    private final PostStore postStore;
    private final PostValidator postValidator;

    @Override
    @Transactional
    public PostRegisterDto.Response createPost(Post initPost) {
        postValidator.validatePost(initPost);
        final Post post = postStore.store(initPost);
        return new PostRegisterDto.Response(post.getId());
    }

    @Override
    @Transactional
    public void deletePost(Long memberId, Long postId) {
        final Post post = postReader.getPostBy(memberId, postId);
        post.deletePost();
    }

    @Override
    @Transactional
    public void updatePost(Long memberId, Long postId, String content) {
        final Post post = postReader.getPostBy(memberId, postId);
        post.updatePost(content);
    }

    @Override
    public Post getPostBy(Long postId) {
        return postReader.getPostBy(postId);
    }

    @Override
    public PostFeedDto getPostFeedDtoBy(Long postId) {
        return postReader.getPostFeedDtoBy(postId);
    }

    @Override
    public Page<PostFeedDto> getHomeFeedDtosBy(Long memberId, Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return postReader.getHomeFeedDtosBy(memberId, pageable);
    }
}
