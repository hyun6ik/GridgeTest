package hyun6ik.gridgetest.interfaces.comment.controller;

import hyun6ik.gridgetest.application.comment.CommentFacade;
import hyun6ik.gridgetest.domain.comment.service.CommentService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.comment.constant.CommentConstraints;
import hyun6ik.gridgetest.interfaces.comment.dto.response.PostCommentResponseDto;
import hyun6ik.gridgetest.interfaces.comment.dto.request.CommentRequestDto;
import hyun6ik.gridgetest.interfaces.comment.dto.response.CommentResponseDto;
import hyun6ik.gridgetest.interfaces.common.dto.LikeDto;
import hyun6ik.gridgetest.interfaces.common.dto.ReportDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "게시글에 댓글을 작성하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@MemberId Long memberId, @PathVariable Long postId, @Valid @RequestBody CommentRequestDto request) {
        return ResponseEntity.ok(commentFacade.createComment(memberId, postId, request.getContent()));
    }

    @Operation(summary = "게시글에 댓글을 삭제하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@MemberId Long memberId, @PathVariable Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return ResponseEntity.ok(CommentConstraints.DELETE_COMMENT);
    }

    @Operation(summary = "게시글에 댓글을 신고하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/reports/{commentId}")
    public ResponseEntity<ReportDto.Response> reportComment(@MemberId Long memberId, @PathVariable Long commentId, @Valid @RequestBody ReportDto.Request request) {
        return ResponseEntity.ok(commentFacade.reportComment(memberId, commentId, request.getReportReason()));
    }

    @Operation(summary = "게시글에 댓글들을 보여주는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Page<PostCommentResponseDto>> getPostCommentDtos(@PathVariable Long postId, Optional<Integer> page) {
        return ResponseEntity.ok(commentService.getPostCommentDtosBy(postId, page));
    }

    @Operation(summary = "게시글에 댓글에 좋아요 누르는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/likes/{commentId}")
    public ResponseEntity<LikeDto> likeComment(@MemberId Long memberId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentFacade.likeComment(memberId, commentId));
    }

    @Operation(summary = "게시글에 댓글을 좋아요를 취소하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/unlikes/{commentId}")
    public ResponseEntity<LikeDto> unlikeComment(@MemberId Long memberId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentFacade.unlikeComment(memberId, commentId));
    }
}
