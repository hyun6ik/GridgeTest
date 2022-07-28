package hyun6ik.gridgetest.interfaces.post.controller;

import hyun6ik.gridgetest.application.post.PostFacade;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.post.dto.PostRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;

    @LoginUser
    @PostMapping("/new")
    public ResponseEntity<PostRegisterDto.Response> createPost(@MemberId Long memberId, @RequestBody PostRegisterDto.Request request) {
        return ResponseEntity.ok(postFacade.createPost(memberId, request));
    }
}
