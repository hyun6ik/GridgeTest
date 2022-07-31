package hyun6ik.gridgetest.domain.chat.entity;

import hyun6ik.gridgetest.domain.base.BaseTimeEntity;
import hyun6ik.gridgetest.domain.chat.constant.ChatRoomStatus;
import hyun6ik.gridgetest.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member host;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Member guest;

    @Embedded
    private Chats chats;

    @Column(length = 200)
    private String lastMessage;

    @Enumerated(EnumType.STRING)
    private ChatRoomStatus chatRoomStatus;

    @Builder
    public ChatRoom(Member host, Member guest) {
        this.host = host;
        this.guest = guest;
        this.chatRoomStatus = ChatRoomStatus.USE;
        this.chats = new Chats(new ArrayList<>());
    }

    public void sendMessage(Member host, Member guest, String message) {
        final Chat chat = new Chat(this, host, guest, message);
        chats.add(chat);
        this.lastMessage = message;
    }
}
