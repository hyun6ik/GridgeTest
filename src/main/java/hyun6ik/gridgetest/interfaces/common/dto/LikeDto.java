package hyun6ik.gridgetest.interfaces.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeDto {

    private Integer likeCount;
    private Boolean liked;

    public LikeDto(Integer likeCount, Boolean liked) {
        this.likeCount = likeCount;
        this.liked = liked;
    }
}
