package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDto {

    private Long postId;
    private Long memberId;
    private String nickName;
    private String content;
    private PostStatus postStatus;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creatTime;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updateTime;
    private List<PostLikeDto> postLikeDtos;
    private List<PostReportDto> postReportDtos;
    private List<CommentDto> commentDtos;

    @QueryProjection
    public PostDto(Long postId, Long memberId,String nickName, String content, PostStatus postStatus, LocalDateTime creatTime, LocalDateTime updateTime) {
        this.postId = postId;
        this.memberId = memberId;
        this.nickName = nickName;
        this.content = content;
        this.postStatus = postStatus;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public void addPostLikeDtos(List<PostLikeDto> postLikeDtos) {
        this.postLikeDtos = postLikeDtos;
    }

    public void addPostReportDtos(List<PostReportDto> postReportDtos) {
        this.postReportDtos = postReportDtos;
    }

    public void addCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }
}
