package hyun6ik.gridgetest.interfaces.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostCommentDto {

    private Long memberId;
    private Long commentId;
    private Integer likeCount;
    private String profileImageUrl;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;

    @QueryProjection
    public PostCommentDto(Long memberId, Long commentId, Integer likeCount, String profileImageUrl, String nickName, String content, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.commentId = commentId;
        this.likeCount = likeCount;
        this.profileImageUrl = profileImageUrl;
        this.nickName = nickName;
        this.content = content;
        this.createdAt = createdAt;
    }
}
