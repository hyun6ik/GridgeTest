package hyun6ik.gridgetest.interfaces.comment.dto.response;

import hyun6ik.gridgetest.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long memberId;
    private String profileImageUrl;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public CommentResponseDto(Long memberId, String profileImageUrl, String nickName, String content, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.profileImageUrl = profileImageUrl;
        this.nickName = nickName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentResponseDto of(Comment comment) {
        return CommentResponseDto.builder()
                .memberId(comment.getMember().getId())
                .profileImageUrl(comment.getMember().getProfile().getImage())
                .nickName(comment.getMember().getNickName())
                .content(comment.getCommentContent().getContent())
                .createdAt(comment.getCreateTime())
                .build();
    }
}
