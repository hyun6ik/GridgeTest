package hyun6ik.gridgetest.domain.post.service;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.infrastructure.post.PostReader;
import hyun6ik.gridgetest.infrastructure.post.PostStore;
import hyun6ik.gridgetest.infrastructure.post.PostValidator;
import hyun6ik.gridgetest.interfaces.post.dto.PostRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
