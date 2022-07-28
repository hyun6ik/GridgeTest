package hyun6ik.gridgetest.interfaces.member.controller;

import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.interfaces.member.constant.MemberConstraints;
import hyun6ik.gridgetest.interfaces.member.dto.PasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDto request) {
        memberService.changePassword(request.getPhoneNumber(), request.getPassword(), request.getPassword2());
        return ResponseEntity.ok(MemberConstraints.CHANGE_PASSWORD);
    }
}
