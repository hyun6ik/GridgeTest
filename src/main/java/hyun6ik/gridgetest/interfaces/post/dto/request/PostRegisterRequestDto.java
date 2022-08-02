package hyun6ik.gridgetest.interfaces.post.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRegisterRequestDto {
    private List<String> imageUrls;
    private String content;
}
