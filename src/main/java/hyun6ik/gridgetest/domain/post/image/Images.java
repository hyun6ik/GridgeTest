package hyun6ik.gridgetest.domain.post.image;

import hyun6ik.gridgetest.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Images {

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images = new ArrayList<>();

    public Images(List<Image> images) {
        this.images = images;
    }

    public void belongTo(Post post) {
        images.forEach(image -> image.belongTo(post));
    }

    public List<String> getImageUrls() {
        return images.stream()
                .map(Image::getUrl)
                .collect(Collectors.toUnmodifiableList());
    }

}
