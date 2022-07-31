package hyun6ik.gridgetest.interfaces.member.controller;

import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.member.constant.MemberConstraints;
import hyun6ik.gridgetest.interfaces.member.dto.FollowDto;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;
import hyun6ik.gridgetest.interfaces.member.dto.PasswordDto;
import hyun6ik.gridgetest.interfaces.member.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto request) {
        memberService.changePassword(request.getPhoneNumber(), request.getPassword(), request.getPassword2());
        return ResponseEntity.ok(MemberConstraints.UPDATE_PASSWORD);
    }

    @LoginUser
    @PatchMapping("/private")
    public ResponseEntity<String> updatePrivateAccount(@MemberId Long memberId) {
        memberService.updatePrivateAccount(memberId);
        return ResponseEntity.ok(MemberConstraints.UPDATE_PRIVATE);
    }

    @LoginUser
    @PatchMapping("/resign")
    public ResponseEntity<String> resignMember(@MemberId Long memberId) {
        memberService.resignMember(memberId);
        return ResponseEntity.ok(MemberConstraints.RESIGN_MEMBER);
    }

    @LoginUser
    @PostMapping("/followings/{memberId}")
    public ResponseEntity<FollowDto> followMember(@MemberId Long fromId, @PathVariable("memberId") Long toId) {
        return ResponseEntity.ok(memberService.followMember(fromId, toId));
    }

    @LoginUser
    @PatchMapping("/followings/{memberId}")
    public ResponseEntity<FollowDto> unfollowMember(@MemberId Long fromId, @PathVariable("memberId") Long toId) {
        return ResponseEntity.ok(memberService.unfollowMember(fromId, toId));
    }

    @LoginUser
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(@MemberId Long memberId, Optional<Integer> page) {
        return ResponseEntity.ok(memberService.getMyPageDtoBy(memberId, page));
    }

    @LoginUser
    @PatchMapping("/profiles/website")
    public ResponseEntity<String> updateProfileWebsite(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Website request) {
        memberService.updateProfileWebsite(memberId, request.getWebsiteUrl());
        return ResponseEntity.ok(MemberConstraints.UPDATE_WEBSITE);
    }

    @LoginUser
    @PatchMapping("/profiles/introduce")
    public ResponseEntity<String> updateProfileIntroduce(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Introduce request) {
        memberService.updateProfileIntroduce(memberId, request.getIntroduce());
        return ResponseEntity.ok(MemberConstraints.UPDATE_INTRODUCE);
    }

    @LoginUser
    @PatchMapping("/profiles/image")
    public ResponseEntity<String> updateProfileImage(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Image request) {
        memberService.updateProfileImage(memberId, request.getImage());
        return ResponseEntity.ok(MemberConstraints.UPDATE_IMAGE);
    }

    @LoginUser
    @PatchMapping("/profiles/name")
    public ResponseEntity<String> updateProfileName(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Name request) {
        memberService.updateProfileName(memberId, request.getName());
        return ResponseEntity.ok(MemberConstraints.UPDATE_NAME);
    }
}
