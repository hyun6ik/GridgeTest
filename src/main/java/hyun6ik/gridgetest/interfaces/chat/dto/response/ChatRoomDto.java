package hyun6ik.gridgetest.interfaces.chat.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDto {

    private Long memberId;
    private String profile;
    private String message;
    private LocalDateTime messageTime;

    @QueryProjection
    public ChatRoomDto(Long memberId, String profile, String message, LocalDateTime messageTime) {
        this.memberId = memberId;
        this.profile = profile;
        this.message = message;
        this.messageTime = messageTime;
    }
}
