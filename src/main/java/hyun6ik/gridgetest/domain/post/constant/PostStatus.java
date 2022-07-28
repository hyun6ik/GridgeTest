package hyun6ik.gridgetest.domain.post.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    USE("사용"),DELETE("삭제"),HOLD("보관");

    private final String description;
}
