package hyun6ik.gridgetest.domain.login.vo;

import hyun6ik.gridgetest.domain.member.constant.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SocialUserInfo {

    private final String name;
    private final MemberType memberType;

    @Builder
    public SocialUserInfo(String name, MemberType memberType) {
        this.name = name;
        this.memberType = memberType;
    }
}
