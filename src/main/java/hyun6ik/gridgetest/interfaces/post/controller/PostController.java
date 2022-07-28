package hyun6ik.gridgetest.interfaces.post.controller;

import hyun6ik.gridgetest.application.post.PostFacade;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.post.dto.PostRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
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
    @PutMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@MemberId Long memberId, @PathVariable Long postId) {
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok("DELETE");
    }
}
