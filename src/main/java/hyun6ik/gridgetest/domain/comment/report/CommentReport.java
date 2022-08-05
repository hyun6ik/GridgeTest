package hyun6ik.gridgetest.domain.comment.report;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.report.PostReport;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportReason reportReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public CommentReport(ReportReason reportReason, Comment comment, Member member) {
        this.reportReason = reportReason;
        this.comment = comment;
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentReport commentReport = (CommentReport) o;
        return Objects.equals(comment, commentReport.getComment()) &&
                Objects.equals(member, commentReport.getMember());
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, member);
    }

    public boolean isMyComment() {
        return Objects.equals(comment.getMember(), member);
    }
}
