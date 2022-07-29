package hyun6ik.gridgetest.interfaces.comment.controller;

import hyun6ik.gridgetest.application.comment.CommentFacade;
import hyun6ik.gridgetest.domain.comment.service.CommentService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.comment.constant.CommentConstraints;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;
    private final CommentService commentService;

    @LoginUser
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto.Response> createComment(@MemberId Long memberId, @PathVariable Long postId, @RequestBody CommentDto.Request request) {
        return ResponseEntity.ok(commentFacade.createComment(memberId, postId, request.getContent()));
    }

    @LoginUser
    @PatchMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@MemberId Long memberId, @PathVariable Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return ResponseEntity.ok(CommentConstraints.DELETE_COMMENT);
    }
}
