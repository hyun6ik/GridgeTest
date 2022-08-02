package hyun6ik.gridgetest.interfaces.member.controller;

import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.global.annotation.LoginUser;
import hyun6ik.gridgetest.global.annotation.MemberId;
import hyun6ik.gridgetest.interfaces.member.constant.MemberConstraints;
import hyun6ik.gridgetest.interfaces.member.dto.FollowerDto;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;
import hyun6ik.gridgetest.interfaces.member.dto.PasswordDto;
import hyun6ik.gridgetest.interfaces.member.dto.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "비밀번호를 변경하는 API")
    @PostMapping("/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordDto request) {
        memberService.changePassword(request.getPhoneNumber(), request.getPassword(), request.getPassword2());
        return ResponseEntity.ok(MemberConstraints.UPDATE_PASSWORD);
    }

    @Operation(summary = "비공개 계정으로 전환하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/private")
    public ResponseEntity<String> updatePrivateAccount(@MemberId Long memberId) {
        memberService.updatePrivateAccount(memberId);
        return ResponseEntity.ok(MemberConstraints.UPDATE_PRIVATE);
    }

    @Operation(summary = "회원 탈퇴하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/resign")
    public ResponseEntity<String> resignMember(@MemberId Long memberId) {
        memberService.resignMember(memberId);
        return ResponseEntity.ok(MemberConstraints.RESIGN_MEMBER);
    }

    @Operation(summary = "팔로우 하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PostMapping("/followings/{memberId}")
    public ResponseEntity<FollowerDto> followMember(@MemberId Long fromId, @PathVariable("memberId") Long toId) {
        return ResponseEntity.ok(memberService.followMember(fromId, toId));
    }

    @Operation(summary = "언팔로우 하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/followings/{memberId}")
    public ResponseEntity<String> unfollowMember(@MemberId Long fromId, @PathVariable("memberId") Long toId) {
        memberService.unfollowMember(fromId, toId);
        return ResponseEntity.ok(MemberConstraints.UNFOLLOW_MEMBER);
    }

    @Operation(summary = "비공개 계정이 팔로우를 수락하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/followings/private/{followId}")
    public ResponseEntity<String> privateApproveFollowMember(@PathVariable Long followId) {
        memberService.privateApproveFollowMember(followId);
        return ResponseEntity.ok(MemberConstraints.PRIVATE_APPROVE_MEMBER);
    }

    @Operation(summary = "비공개 계정이 팔로우를 거절하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @DeleteMapping("/followings/private/{followId}")
    public ResponseEntity<String> privateRejectFollowMember(@MemberId Long memberId, @PathVariable Long followId) {
        memberService.privateRejectFollowMember(memberId, followId);
        return ResponseEntity.ok(MemberConstraints.PRIVATE_REJECT_MEMBER);

    }

    @Operation(summary = "마이페이지 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDto> getMyPage(@MemberId Long memberId, Optional<Integer> page) {
        return ResponseEntity.ok(memberService.getMyPageDtoBy(memberId, page));
    }

    @Operation(summary = "프로필정보의 웹사이트를 변경하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/profiles/website")
    public ResponseEntity<String> updateProfileWebsite(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Website request) {
        memberService.updateProfileWebsite(memberId, request.getWebsiteUrl());
        return ResponseEntity.ok(MemberConstraints.UPDATE_WEBSITE);
    }

    @Operation(summary = "프로필정보의 소개글을 변경하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/profiles/introduce")
    public ResponseEntity<String> updateProfileIntroduce(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Introduce request) {
        memberService.updateProfileIntroduce(memberId, request.getIntroduce());
        return ResponseEntity.ok(MemberConstraints.UPDATE_INTRODUCE);
    }

    @Operation(summary = "프로필정보의 사진을 변경하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/profiles/image")
    public ResponseEntity<String> updateProfileImage(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Image request) {
        memberService.updateProfileImage(memberId, request.getImage());
        return ResponseEntity.ok(MemberConstraints.UPDATE_IMAGE);
    }

    @Operation(summary = "프로필정보의 이름을 변경하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/profiles/name")
    public ResponseEntity<String> updateProfileName(@MemberId Long memberId, @Valid @RequestBody ProfileDto.Name request) {
        memberService.updateProfileName(memberId, request.getName());
        return ResponseEntity.ok(MemberConstraints.UPDATE_NAME);
    }

    @Operation(summary = "프로필정보의 사용자 이름을 변경하는 API", security = {@SecurityRequirement(name = "BearerKey")})
    @LoginUser
    @PatchMapping("/profiles/nickName")
    public ResponseEntity<String> updateProfileNickName(@MemberId Long memberId, @Valid @RequestBody ProfileDto.NickName request) {
        memberService.updateProfileNickName(memberId, request.getNickName());
        return ResponseEntity.ok(MemberConstraints.UPDATE_NICKNAME);
    }
}
