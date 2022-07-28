package hyun6ik.gridgetest.interfaces.post.dto;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.content.PostContent;
import hyun6ik.gridgetest.domain.post.image.Image;
import hyun6ik.gridgetest.domain.post.image.Images;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDtoAssembler {

    public static Post toEntity(Member member, PostRegisterDto.Request request) {
        final Images images = new Images(request.getImageUrls()
                .stream()
                .map(Image::new)
                .collect(Collectors.toList()));

        final PostContent postContent = new PostContent(request.getContent());

        return Post.builder()
                .postContent(postContent)
                .images(images)
                .member(member)
                .build();
    }
}
