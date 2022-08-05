package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.gridgetest.domain.comment.constant.CommentStatus;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentReportDto {

    private Long reportId;
    private Long commentId;
    private String nickName;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime reportDate;
    private ReportReason reportReason;
    private CommentStatus commentStatus;

    @QueryProjection
    public CommentReportDto(Long reportId, Long commentId, String nickName, LocalDateTime reportDate, ReportReason reportReason, CommentStatus commentStatus) {
        this.reportId = reportId;
        this.commentId = commentId;
        this.nickName = nickName;
        this.reportDate = reportDate;
        this.reportReason = reportReason;
        this.commentStatus = commentStatus;
    }
}
