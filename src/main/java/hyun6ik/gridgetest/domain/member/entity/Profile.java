package hyun6ik.gridgetest.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String nickName;

    private String image;

    private String webSite;

    private String introduce;

    @Builder
    public Profile(String name, String nickName, String image, String webSite, String introduce) {
        this.name = name;
        this.nickName = nickName;
        this.image = image;
        this.webSite = webSite;
        this.introduce = introduce;
    }

    public void updateWebSite(String website) {
        this.webSite = website;
    }
}
