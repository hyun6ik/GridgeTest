package hyun6ik.gridgetest.application.comment;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.comment.service.CommentService;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import hyun6ik.gridgetest.interfaces.post.dto.ReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentFacade {

    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public CommentDto.Response createComment(Long memberId, Long postId, String content) {
        final Member member = memberService.getMemberBy(memberId);
        final Post post = postService.getPostBy(postId);
        return commentService.createComment(member, post, content);
    }

    @Transactional
    public ReportDto.Response reportComment(Long memberId, Long commentId, ReportReason reportReason) {
        final Member member = memberService.getMemberBy(memberId);
        final Comment comment = commentService.getCommentBy(commentId);
        comment.report(member, reportReason);
        return new ReportDto.Response(comment.getReportCounts());
    }
}
