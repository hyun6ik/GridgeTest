package hyun6ik.gridgetest.domain.member.constant;

public enum MemberType {

    GENERAL, KAKAO;

    public static MemberType from(String type) {
        return MemberType.valueOf(type.toUpperCase());
    }
}
