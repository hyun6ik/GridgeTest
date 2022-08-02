package hyun6ik.gridgetest.interfaces.login.dto.request;

import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.domain.member.constant.MemberScope;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.entity.MemberStatus;
import hyun6ik.gridgetest.domain.member.entity.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequestDto {

    @NotBlank(message = "핸드폰 번호는 필수 입니다.")
    private String phoneNumber;
    @Size(max = 20, message = "사용자 이름은 최대 20자 까지 입니다.")
    @NotBlank(message = "사용자 이름은 필수 입니다.")
    private String nickName;
    @NotBlank(message = "회원 타입은 필수 입니다.")
    private String memberType;
    private LocalDate birthDay;
    @Size(max = 20, message = "이름은 최대 20자 까지 입니다.")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 까지 입니다.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Member toEntity() {
        final Profile profile = Profile.builder()
                .name(name)
                .nickName(nickName)
                .build();
        final MemberStatus memberStatus = MemberStatus.builder()
                .memberType(MemberType.from(memberType))
                .memberRole(MemberRole.USER)
                .memberScope(MemberScope.PUBLIC)
                .memberCondition(MemberCondition.NORMAL)
                .build();
        return Member.builder()
                .memberStatus(memberStatus)
                .profile(profile)
                .birthDay(birthDay)
                .phoneNumber(phoneNumber)
                .password(StringUtils.hasText(password) ? password : null)
                .build();
    }
}
