package hyun6ik.gridgetest.interfaces.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.domain.member.constant.MemberScope;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private Long memberId;
    private String name;
    private String nickName;
    private String phoneNumber;
    private LocalDate birthDay;
    private MemberCondition memberCondition;
    private MemberRole memberRole;
    private MemberScope memberScope;
    private MemberType memberType;
    private String profileImage;
    private String profileIntroduce;
    private String profileWebSite;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createDate;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updateDate;

    @QueryProjection
    public MemberDto(Long memberId, String name, String nickName, String phoneNumber, LocalDate birthDay, MemberCondition memberCondition, MemberRole memberRole, MemberScope memberScope, MemberType memberType, String profileImage, String profileIntroduce, String profileWebSite, LocalDateTime createDate, LocalDateTime updateDate) {
        this.memberId = memberId;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.memberCondition = memberCondition;
        this.memberRole = memberRole;
        this.memberScope = memberScope;
        this.memberType = memberType;
        this.profileImage = profileImage;
        this.profileIntroduce = profileIntroduce;
        this.profileWebSite = profileWebSite;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
