package hyun6ik.gridgetest.application.admin;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.comment.service.CommentService;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentDeleteDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostDeleteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminFacade {

    private final PostService postService;
    private final CommentService commentService;

    @Transactional
    public PostDeleteDto deleteReportPost(Long postId) {
        final Post post = postService.getPostBy(postId);
        post.validateIsReported();
        post.deletePost();;
        return new PostDeleteDto(post.getId());
    }

    @Transactional
    public CommentDeleteDto deleteComment(Long commentId) {
        final Comment comment = commentService.getCommentBy(commentId);
        comment.validateIsReported();
        comment.delete();
        return new CommentDeleteDto(comment.getId());
    }

    @Transactional
    public PostDeleteDto deletePost(Long postId) {
        final Post post = postService.getPostBy(postId);
        post.deletePost();;
        return new PostDeleteDto(post.getId());
    }
}
