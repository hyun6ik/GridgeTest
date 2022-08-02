package hyun6ik.gridgetest.interfaces.post.controller;

import hyun6ik.gridgetest.application.post.PostFacade;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.common.dto.LikeDto;
import hyun6ik.gridgetest.interfaces.common.dto.ReportDto;
import hyun6ik.gridgetest.interfaces.post.constant.PostConstraints;
import hyun6ik.gridgetest.interfaces.post.dto.request.PostRegisterRequestDto;
import hyun6ik.gridgetest.interfaces.post.dto.request.PostUpdateRequestDto;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostFeedDto;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostRegisterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final PostService postService;

    @Operation(summary = "게시글을 생성하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/new")
    public ResponseEntity<PostRegisterResponseDto> createPost(@MemberId Long memberId, @Valid @RequestBody PostRegisterRequestDto request) {
        return ResponseEntity.ok(postFacade.createPost(memberId, request));
    }

    @Operation(summary = "게시글을 삭제하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@MemberId Long memberId, @PathVariable Long postId) {
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok(PostConstraints.DELETE);
    }

    @Operation(summary = "게시글을 수정하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(@MemberId Long memberId, @PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto request) {
        postService.updatePost(memberId, postId, request.getContent());
        return ResponseEntity.ok(PostConstraints.UPDATE);
    }

    @Operation(summary = "게시글 좋아요를 누르는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/likes/{postId}")
    public ResponseEntity<LikeDto> likePost(@MemberId Long memberId, @PathVariable Long postId) {
        return ResponseEntity.ok(postFacade.likePost(memberId, postId));
    }

    @Operation(summary = "게시글을 좋아요를 취소하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/unlikes/{postId}")
    public ResponseEntity<LikeDto> unlikePost(@MemberId Long memberId, @PathVariable Long postId) {
        return ResponseEntity.ok(postFacade.unlikePost(memberId, postId));
    }

    @Operation(summary = "게시글을 신고하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/reports/{postId}")
    public ResponseEntity<ReportDto.Response> reportPost(@MemberId Long memberId, @PathVariable Long postId, @Valid @RequestBody ReportDto.Request request) {
        return ResponseEntity.ok(postFacade.reportPost(memberId, postId, request.getReportReason()));
    }

    @Operation(summary = "게시글 단건 상세보기 API")
    @GetMapping("/{postId}")
    public ResponseEntity<PostFeedDto> getPostFeed(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostFeedDtoBy(postId));
    }

    @Operation(summary = "홈 - 게시글(피드)들을 가져오는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @GetMapping("/home")
    public ResponseEntity<Page<PostFeedDto>> getHomeFeeds(@MemberId Long memberId, Optional<Integer> page) {
        return ResponseEntity.ok(postService.getHomeFeedDtosBy(memberId, page));
    }

}
