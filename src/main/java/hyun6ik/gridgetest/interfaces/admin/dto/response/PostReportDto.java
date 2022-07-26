package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostReportDto {

    private Long reportId;
    private Long boardId;
    private String nickName;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime reportDate;
    private ReportReason reportReason;
    private PostStatus postStatus;

    @QueryProjection
    public PostReportDto(Long reportId, Long boardId, String nickName, LocalDateTime reportDate, ReportReason reportReason, PostStatus postStatus) {
        this.reportId = reportId;
        this.boardId = boardId;
        this.nickName = nickName;
        this.reportDate = reportDate;
        this.reportReason = reportReason;
        this.postStatus = postStatus;
    }
}
