package hyun6ik.gridgetest.domain.chat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomStatus {

    USE("사용중"), DELETE("삭제된");

    private final String description;
}
