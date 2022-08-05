package hyun6ik.gridgetest.interfaces.admin.controller;

import hyun6ik.gridgetest.application.admin.AdminFacade;
import hyun6ik.gridgetest.domain.admin.service.AdminService;
import hyun6ik.gridgetest.global.annotation.AdminUser;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentDeleteDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostDeleteDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
import hyun6ik.gridgetest.interfaces.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AdminFacade adminFacade;

    @Operation(summary = "관리자 - 신고된 게시글 조회 API", security = {@SecurityRequirement(name = "BearerKey")})
    @AdminUser
    @GetMapping("/reports/posts")
    public ApiResponse<Page<PostReportDto>> getBoardReports(Optional<Integer> page) {
        return ApiResponse.success(adminService.getPostReportDtos(page));
    }

    @Operation(summary = "관리자 - 신고된 게시글 삭제하기 API", security = {@SecurityRequirement(name = "BearerKey")})
    @PatchMapping("/reports/posts/{postId}")
    public ApiResponse<PostDeleteDto> deletePost(@PathVariable Long postId) {
        return ApiResponse.success(adminFacade.deletePost(postId));
    }

    @Operation(summary = "관리자 - 신고된 댓글 조회 API", security = {@SecurityRequirement(name = "BearerKey")})
    @AdminUser
    @GetMapping("/reports/comments")
    public ApiResponse<Page<CommentReportDto>> getCommentReports(Optional<Integer> page) {
        return ApiResponse.success(adminService.getCommentReportDtos(page));
    }

    @Operation(summary = "관리자 - 신고된 댓글 삭제하기 API", security = {@SecurityRequirement(name = "BearerKey")})
    @PatchMapping("/reports/comments/{commentId}")
    public ApiResponse<CommentDeleteDto> deleteComment(@PathVariable Long commentId) {
        return ApiResponse.success(adminFacade.deleteComment(commentId));
    }
}
