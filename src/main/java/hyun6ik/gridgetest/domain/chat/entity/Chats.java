package hyun6ik.gridgetest.domain.chat.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chats {

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Chat> chats;

    public Chats(List<Chat> chats) {
        this.chats = chats;
    }

    public void add(Chat chat) {
        chats.add(chat);
    }

    public void remove(Chat chat) {
        chats.remove(chat);
    }
}
