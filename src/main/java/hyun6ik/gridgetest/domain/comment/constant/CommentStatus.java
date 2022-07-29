package hyun6ik.gridgetest.domain.comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentStatus {

    USE("사용중"), DELETE("삭제"),BLOCK("신고");

    private final String description;
}
