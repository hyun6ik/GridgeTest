package hyun6ik.gridgetest.domain.member.entity;

import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.constant.MemberRole;
import hyun6ik.gridgetest.domain.member.constant.MemberScope;
import hyun6ik.gridgetest.domain.member.constant.MemberType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberStatus {

    @Enumerated(EnumType.STRING)
    private MemberScope memberScope;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private MemberCondition memberCondition;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Builder
    public MemberStatus(MemberScope memberScope, MemberRole memberRole, MemberCondition memberCondition, MemberType memberType) {
        this.memberScope = memberScope;
        this.memberRole = memberRole;
        this.memberCondition = memberCondition;
        this.memberType = memberType;
    }

    public void changePrivateAccount() {
        this.memberScope = MemberScope.PRIVATE;
    }
}
