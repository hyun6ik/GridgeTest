package hyun6ik.gridgetest.domain.comment.service;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.comment.entity.CommentContent;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.infrastructure.comment.CommentReader;
import hyun6ik.gridgetest.infrastructure.comment.CommentStore;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentReader commentReader;
    private final CommentStore commentStore;

    @Override
    @Transactional
    public CommentDto.Response createComment(Member member, Post post, String content) {
        final CommentContent commentContent = new CommentContent(content);
        final Comment initComment = new Comment(commentContent, member, post);
        final Comment comment = commentStore.store(initComment);
        return CommentDto.Response.of(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long memberId, Long commentId) {
        final Comment comment = commentReader.getCommentBy(memberId, commentId);
        comment.delete();
    }
}
