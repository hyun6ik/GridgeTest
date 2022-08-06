package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.gridgetest.domain.comment.constant.CommentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long commentId;
    private Long postId;
    private Long memberId;
    private String nickName;
    private String content;
    private CommentStatus commentStatus;
    private Integer commentCount;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @QueryProjection
    public CommentDto(Long commentId, Long postId, Long memberId, String nickName, String content, CommentStatus commentStatus, Integer commentCount, Integer likeCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.nickName = nickName;
        this.content = content;
        this.commentStatus = commentStatus;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
