package hyun6ik.gridgetest.infrastructure.comment;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.comment.repository.CommentQueryRepository;
import hyun6ik.gridgetest.interfaces.comment.dto.response.PostCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

    private final CommentQueryRepository commentQueryRepository;

    public Comment getCommentBy(Long memberId, Long commentId) {
        return commentQueryRepository.findByIdAndCommentId(memberId, commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_COMMENT));
    }

    public Comment getCommentBy(Long commentId) {
        return commentQueryRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_COMMENT));
    }

    public Page<PostCommentResponseDto> getPostCommentDtosBy(Long postId, Pageable pageable) {
        return commentQueryRepository.findPostCommentDtosBy(postId, pageable);
    }
}
