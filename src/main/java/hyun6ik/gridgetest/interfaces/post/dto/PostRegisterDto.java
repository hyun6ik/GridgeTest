package hyun6ik.gridgetest.interfaces.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PostRegisterDto {

    @Getter
    @Setter
    public static class Request {
        private List<String> imageUrls;
        private String content;
    }

    @Getter
    @Setter
    public static class Response {
        private Long postId;

        public Response(Long postId) {
            this.postId = postId;
        }
    }
}
