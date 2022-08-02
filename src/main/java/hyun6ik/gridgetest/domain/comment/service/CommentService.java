package hyun6ik.gridgetest.domain.comment.service;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.interfaces.comment.dto.response.PostCommentResponseDto;
import hyun6ik.gridgetest.interfaces.comment.dto.response.CommentResponseDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CommentService {
    CommentResponseDto createComment(Member member, Post post, String content);

    void deleteComment(Long memberId, Long commentId);

    Comment getCommentBy(Long commentId);

    Page<PostCommentResponseDto> getPostCommentDtosBy(Long postId, Optional<Integer> page);
}
