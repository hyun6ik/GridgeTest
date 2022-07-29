package hyun6ik.gridgetest.interfaces.comment.dto;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CommentDto {

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "댓글을 입력해주세요.")
        private String content;
    }


    @Getter
    @Setter
    public static class Response {
        private Long memberId;
        private String profileImageUrl;
        private String nickName;
        private String content;

        @Builder
        public Response(Long memberId, String profileImageUrl, String nickName, String content) {
            this.memberId = memberId;
            this.profileImageUrl = profileImageUrl;
            this.nickName = nickName;
            this.content = content;
        }

        public static Response of(Comment comment) {
            return Response.builder()
                    .memberId(comment.getMember().getId())
                    .profileImageUrl(comment.getMember().getProfile().getImage())
                    .nickName(comment.getMember().getNickName())
                    .content(comment.getCommentContent().getContent())
                    .build();
        }
    }
}