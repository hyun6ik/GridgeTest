package hyun6ik.gridgetest.domain.comment.entity;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.comment.constant.CommentStatus;
import hyun6ik.gridgetest.domain.comment.like.CommentLike;
import hyun6ik.gridgetest.domain.comment.like.CommentLikes;
import hyun6ik.gridgetest.domain.comment.report.CommentReport;
import hyun6ik.gridgetest.domain.comment.report.CommentReports;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import hyun6ik.gridgetest.global.error.exception.CommentException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.ReportException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CommentContent commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    @Embedded
    private CommentReports commentReports;

    @Embedded
    private CommentLikes commentLikes;

    @Builder
    public Comment(CommentContent commentContent, Member member, Post post) {
        this.commentContent = commentContent;
        this.member = member;
        this.post = post;
        this.commentStatus = CommentStatus.USE;
        this.commentReports = new CommentReports(new ArrayList<>());
        this.commentLikes = new CommentLikes(new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void delete() {
        if (this.commentStatus == CommentStatus.DELETE) {
            throw new CommentException(ErrorCode.ALREADY_DELETE_COMMENT);
        }
        this.commentStatus = CommentStatus.DELETE;
    }

    public void report(Member member, ReportReason reportReason) {
        final CommentReport commentReport = new CommentReport(reportReason, this, member);
        commentReports.add(commentReport);
    }

    public void block() {
        this.commentStatus = CommentStatus.BLOCK;
    }

    public Integer getReportCounts() {
        return commentReports.getCounts();
    }

    public Integer getLikeCounts() {
        return commentLikes.getCounts();
    }

    public void like(Member member) {
        final CommentLike commentLike = new CommentLike(this, member);
        commentLikes.add(commentLike);
    }

    public void unlike(Member member) {
        final CommentLike commentLike = new CommentLike(this, member);
        commentLikes.remove(commentLike);
    }

    public void validateIsReported() {
        if (this.commentReports.getCounts() == 0) {
            throw new ReportException(ErrorCode.NOT_REPORT_COMMENT);
        }
    }
}
