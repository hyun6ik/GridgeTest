package hyun6ik.gridgetest.interfaces.post.dto;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.content.PostContent;
import hyun6ik.gridgetest.domain.post.image.Image;
import hyun6ik.gridgetest.domain.post.image.Images;
import hyun6ik.gridgetest.interfaces.post.dto.request.PostRegisterRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDtoAssembler {

    public static Post toEntity(Member member, PostRegisterRequestDto request) {
        final Image image = new Image(request.getImageUrls().get(0), true);
        final List<Image> imageList = request.getImageUrls()
                .stream()
                .skip(1)
                .map(url -> new Image(url, false))
                .collect(Collectors.toList());

        imageList.add(0, image);

        final Images images = new Images(imageList);

        final PostContent postContent = new PostContent(request.getContent());

        return Post.builder()
                .postContent(postContent)
                .images(images)
                .member(member)
                .build();
    }
}
