package hyun6ik.gridgetest.infrastructure.member;

import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.LoginException;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberQueryRepository;
import hyun6ik.gridgetest.infrastructure.member.repository.MemberRepository;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberReader {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    public Member getMemberBy(String nickName) {
        return memberRepository.findByProfile_NickName(nickName)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER));
    }

    public Member findSocialMemberBy(SocialUserInfo socialUserInfo) {
        return memberQueryRepository.findSocialMemberBy(socialUserInfo)
                .orElseThrow(() -> new LoginException(ErrorCode.NOT_FOUND_SOCIAL_USER));
    }

    public Member getMemberBy(Long memberId) {
        return memberQueryRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    public Member getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    public MyPageDto getMyPageDtoBy(Long memberId, Pageable pageable) {
        final MyPageDto myPageDto = memberQueryRepository.findMyPageDtoBy(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
        myPageDto.addPageDto(memberQueryRepository.findMyPagePostDtosBy(memberId, pageable));
        return myPageDto;
    }
}
