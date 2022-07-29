package hyun6ik.gridgetest.domain.comment.service;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;

public interface CommentService {
    CommentDto.Response createComment(Member member, Post post, String content);

    void deleteComment(Long memberId, Long commentId);

    Comment getCommentBy(Long commentId);
}
