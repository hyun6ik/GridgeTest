package hyun6ik.gridgetest.domain.post.report;

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
public class PostReports {

    private static final int BLOCK_COUNT = 20;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PostReport> postReports;

    public PostReports(List<PostReport> postReports) {
        this.postReports = postReports;
    }

    public void add(PostReport postReport) {
        if (postReport.isMyPost()) {
            throw new ReportException(ErrorCode.CANNOT_REPORT_MY_POST);
        }
        if (postReports.contains(postReport)) {
            throw new ReportException(ErrorCode.CANNOT_REPORT_ALREADY);
        }
        postReports.add(postReport);
        if (postReports.size() > BLOCK_COUNT) {
            postReport.getPost().block();
        }
    }

    public Integer getCounts() {
        return postReports.size();
    }
}
