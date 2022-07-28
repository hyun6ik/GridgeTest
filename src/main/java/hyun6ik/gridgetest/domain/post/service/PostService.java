package hyun6ik.gridgetest.domain.post.service;

import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.interfaces.post.dto.PostRegisterDto;

public interface PostService {

    PostRegisterDto.Response createPost(Post initPost);
}
