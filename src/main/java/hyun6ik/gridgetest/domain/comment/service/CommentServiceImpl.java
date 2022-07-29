package hyun6ik.gridgetest.domain.comment.service;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.domain.comment.entity.CommentContent;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.infrastructure.comment.CommentReader;
import hyun6ik.gridgetest.infrastructure.comment.CommentStore;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import hyun6ik.gridgetest.interfaces.comment.dto.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    public Comment getCommentBy(Long commentId) {
        return commentReader.getCommentBy(commentId);
    }

    @Override
    public Page<PostCommentDto> getPostCommentDtosBy(Long postId, Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return commentReader.getPostCommentDtosBy(postId, pageable);
    }
}
