package hyun6ik.gridgetest.domain.comment.report;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.ReportException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReports {

    private static final int BLOCK_COUNT = 20;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CommentReport> commentReports;

    public CommentReports(List<CommentReport> commentReports) {
        this.commentReports = commentReports;
    }

    public void add(CommentReport commentReport) {
        if (commentReport.isMyComment()) {
            throw new ReportException(ErrorCode.CANNOT_REPORT_MY_COMMENT);
        }
        if (commentReports.contains(commentReport)) {
            throw new ReportException(ErrorCode.ALREADY_REPORT_COMMENT);
        }
        commentReports.add(commentReport);
        if (commentReports.size() > BLOCK_COUNT) {
            commentReport.getComment().block();
        }
    }

    public Integer getCounts() {
        return commentReports.size();
    }
}
