package hyun6ik.gridgetest.interfaces.comment.controller;

import hyun6ik.gridgetest.application.comment.CommentFacade;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @LoginUser
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto.Response> createComment(@MemberId Long memberId, @PathVariable Long postId, @RequestBody CommentDto.Request request) {
        return ResponseEntity.ok(commentFacade.createComment(memberId, postId, request.getContent()));
    }
}
