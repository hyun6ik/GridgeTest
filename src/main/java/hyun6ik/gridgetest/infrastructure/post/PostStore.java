package hyun6ik.gridgetest.infrastructure.post;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.infrastructure.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostStore {

    private final PostRepository postRepository;

    public Post store(Post initPost) {
        return postRepository.save(initPost);
    }
}
