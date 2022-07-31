package hyun6ik.gridgetest.interfaces.comment.controller;

import hyun6ik.gridgetest.application.comment.CommentFacade;
import hyun6ik.gridgetest.domain.comment.service.CommentService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.comment.constant.CommentConstraints;
import hyun6ik.gridgetest.interfaces.comment.dto.CommentDto;
import hyun6ik.gridgetest.interfaces.comment.dto.PostCommentDto;
import hyun6ik.gridgetest.interfaces.common.dto.LikeDto;
import hyun6ik.gridgetest.interfaces.common.dto.ReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;
    private final CommentService commentService;

    @LoginUser
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto.Response> createComment(@MemberId Long memberId, @PathVariable Long postId, @Valid @RequestBody CommentDto.Request request) {
        return ResponseEntity.ok(commentFacade.createComment(memberId, postId, request.getContent()));
    }

    @LoginUser
    @PatchMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@MemberId Long memberId, @PathVariable Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return ResponseEntity.ok(CommentConstraints.DELETE_COMMENT);
    }

    @LoginUser
    @PostMapping("/reports/{commentId}")
    public ResponseEntity<ReportDto.Response> reportComment(@MemberId Long memberId, @PathVariable Long commentId, @Valid @RequestBody ReportDto.Request request) {
        return ResponseEntity.ok(commentFacade.reportComment(memberId, commentId, request.getReportReason()));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Page<PostCommentDto>> getPostCommentDtos(@PathVariable Long postId, Optional<Integer> page) {
        return ResponseEntity.ok(commentService.getPostCommentDtosBy(postId, page));
    }

    @LoginUser
    @PostMapping("/likes/{commentId}")
    public ResponseEntity<LikeDto> likeComment(@MemberId Long memberId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentFacade.likeComment(memberId, commentId));
    }

    @LoginUser
    @PatchMapping("/unlikes/{commentId}")
    public ResponseEntity<LikeDto> unlikeComment(@MemberId Long memberId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentFacade.unlikeComment(memberId, commentId));
    }
}
