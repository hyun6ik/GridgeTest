package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostLikeDto {

    private Long postLikeId;
    private Long postId;
    private Long memberId;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creatTime;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updateTime;

    @QueryProjection
    public PostLikeDto(Long postLikeId, Long postId, Long memberId, LocalDateTime creatTime, LocalDateTime updateTime) {
        this.postLikeId = postLikeId;
        this.postId = postId;
        this.memberId = memberId;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }
}
