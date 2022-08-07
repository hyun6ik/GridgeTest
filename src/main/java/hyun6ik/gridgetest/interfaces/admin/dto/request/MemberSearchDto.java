package hyun6ik.gridgetest.interfaces.admin.dto.request;

import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MemberSearchDto {

    private String searchName = "";
    private String searchNickName = "";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate searchDate;
    private MemberCondition memberCondition;
}
