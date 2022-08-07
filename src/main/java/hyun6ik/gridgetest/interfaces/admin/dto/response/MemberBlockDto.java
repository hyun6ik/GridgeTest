package hyun6ik.gridgetest.interfaces.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBlockDto {

    private Long memberId;
    private String message;

    public MemberBlockDto(Long memberId) {
        this.memberId = memberId;
        this.message = "(관리자) 회원 상태가 정지 상태로 변경되었습니다.";
    }
}
