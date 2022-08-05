package hyun6ik.gridgetest.interfaces.admin.controller;

import hyun6ik.gridgetest.domain.admin.service.AdminService;
import hyun6ik.gridgetest.global.annotation.AdminUser;
import hyun6ik.gridgetest.interfaces.admin.dto.PostReportDto;
import hyun6ik.gridgetest.interfaces.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "관리자 - 신고된 게시글 조회 API", security = {@SecurityRequirement(name = "BearerKey")})
    @AdminUser
    @GetMapping("/reports/boards")
    public ApiResponse<Page<PostReportDto>> getBoardReports(Optional<Integer> page) {
        return ApiResponse.success(adminService.getBoardReportDtos(page));
    }
}
