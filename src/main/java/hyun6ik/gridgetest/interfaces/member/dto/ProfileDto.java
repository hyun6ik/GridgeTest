package hyun6ik.gridgetest.interfaces.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProfileDto {

    @Getter
    @Setter
    public static class Website {
        @Size(max = 100, message = "url은 최대 100자까지 입니다.")
        private String websiteUrl;
    }

    @Getter
    @Setter
    public static class Introduce {
        @Size(max = 300, message = "프로필 소개글은 최대 300자까지 입니다.")
        private String introduce;
    }

    @Getter
    @Setter
    public static class Image {
        private String image;
    }

    @Getter
    @Setter
    public static class Name {
        @Size(max = 20, message = "이름은 최대 20자 까지 입니다.")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
    }

    @Getter
    @Setter
    public static class NickName {
        @Size(max = 20, message = "사용자 이름은 최대 20자 까지 입니다.")
        @NotBlank(message = "사용자 이름은 필수 입니다.")
        private String nickName;
    }







}
