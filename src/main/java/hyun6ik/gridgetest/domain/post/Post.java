package hyun6ik.gridgetest.domain.post;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.comment.entity.Comments;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.domain.post.content.PostContent;
import hyun6ik.gridgetest.domain.post.image.Images;
import hyun6ik.gridgetest.domain.post.like.Like;
import hyun6ik.gridgetest.domain.post.like.Likes;
import hyun6ik.gridgetest.domain.post.report.PostReport;
import hyun6ik.gridgetest.domain.post.report.PostReports;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Images images;

    @Embedded
    private PostContent postContent;

    @Embedded
    private Likes likes;

    @Embedded
    private Comments comments;

    @Embedded
    private PostReports postReports;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Builder
    public Post(Member member, Images images, PostContent postContent) {
        this.member = member;
        this.images = images;
        this.postContent = postContent;
        this.postStatus = PostStatus.USE;

        this.likes = new Likes(new ArrayList<>());
        this.comments = new Comments(new ArrayList<>());
        this.postReports = new PostReports(new ArrayList<>());

        images.belongTo(this);
    }

    public void deletePost() {
        this.postStatus = PostStatus.DELETE;
    }

    public void updatePost(String content) {
        this.postContent = new PostContent(content);
    }

    public void like(Member member) {
        final Like like = new Like(this, member);
        likes.add(like);
    }

    public Integer getLikeCounts() {
        return likes.getCounts();
    }

    public void unlike(Member member) {
        Like like = new Like(this, member);
        likes.remove(like);
    }

    public void block() {
        this.postStatus = PostStatus.BLOCK;
    }

    public void report(Member member, ReportReason reportReason) {
        final PostReport postReport = new PostReport(reportReason, this, member);
        postReports.add(postReport);
    }

    public Integer getReportCounts() {
        return postReports.getCounts();
    }
}
