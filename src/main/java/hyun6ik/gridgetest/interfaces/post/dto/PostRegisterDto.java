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

    public static class Response {
        private Long id;

        public Response(Long id) {
            this.id = id;
        }
    }
}
