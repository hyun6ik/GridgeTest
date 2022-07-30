package hyun6ik.gridgetest.interfaces.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileWebsiteDto {

    @Size(max = 100, message = "url은 최대 100자까지 입니다.")
    private String websiteUrl;
}
