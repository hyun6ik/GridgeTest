package hyun6ik.gridgetest.interfaces.post.controller;

import hyun6ik.gridgetest.application.post.PostFacade;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.post.constant.PostConstraints;
import hyun6ik.gridgetest.interfaces.post.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final PostService postService;

    @LoginUser
    @PostMapping("/new")
    public ResponseEntity<PostRegisterDto.Response> createPost(@MemberId Long memberId, @RequestBody PostRegisterDto.Request request) {
        return ResponseEntity.ok(postFacade.createPost(memberId, request));
    }

    @LoginUser
    @PatchMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@MemberId Long memberId, @PathVariable Long postId) {
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok(PostConstraints.DELETE);
    }

    @LoginUser
    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(@MemberId Long memberId, @PathVariable Long postId, @RequestBody PostUpdateDto.Request request) {
        postService.updatePost(memberId, postId, request.getContent());
        return ResponseEntity.ok(PostConstraints.UPDATE);
    }

    @LoginUser
    @PatchMapping("/likes/{postId}")
    public ResponseEntity<LikeDto> likePost(@MemberId Long memberId, @PathVariable Long postId) {
        return ResponseEntity.ok(postFacade.likePost(memberId, postId));
    }

    @LoginUser
    @PatchMapping("/unlikes/{postId}")
    public ResponseEntity<LikeDto> unlikePost(@MemberId Long memberId, @PathVariable Long postId) {
        return ResponseEntity.ok(postFacade.unlikePost(memberId, postId));
    }

    @LoginUser
    @PostMapping("/reports/{postId}")
    public ResponseEntity<ReportDto.Response> reportPost(@MemberId Long memberId, @PathVariable Long postId, @RequestBody ReportDto.Request request) {
        return ResponseEntity.ok(postFacade.reportPost(memberId, postId, request.getReportReason()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostFeedDto> getPostFeed(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostFeedDtoBy(postId));
    }

    @LoginUser
    @GetMapping("/home")
    public ResponseEntity<Page<PostFeedDto>> getHomeFeeds(@MemberId Long memberId, Optional<Integer> page) {
        return ResponseEntity.ok(postService.getHomeFeedDtosBy(memberId, page));
    }

}
