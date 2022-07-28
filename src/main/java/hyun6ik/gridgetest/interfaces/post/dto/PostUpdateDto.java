package hyun6ik.gridgetest.interfaces.post.dto;

import lombok.Getter;
import lombok.Setter;

public class PostUpdateDto {

    @Getter
    @Setter
    public static class Request {
        private String content;
    }

}
