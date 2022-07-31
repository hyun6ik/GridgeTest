package hyun6ik.gridgetest.domain.chat.entity;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.global.error.exception.BusinessException;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends BaseTimeEntity {

    public static final int MAXIMUM_CONTENT_LENGTH = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member from;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member to;

    @Column(length = 200)
    private String message;

    @Builder
    public Chat(ChatRoom chatRoom, Member from, Member to, String message) {
        validateLengthIsOverThanMaximumContentLength(message);
        this.chatRoom = chatRoom;
        this.from = from;
        this.to = to;
        this.message = message;
    }

    private void validateLengthIsOverThanMaximumContentLength(String content) {
        if (content.length() > MAXIMUM_CONTENT_LENGTH) {
            throw new BusinessException(ErrorCode.MAXIMUM_CONTENT_LENGTH);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chat chat = (Chat) o;
        return Objects.equals(from, chat.getFrom()) &&
                Objects.equals(to, chat.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

}
