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
public class ChatContents {

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ChatContent> chatContents;

    public ChatContents(List<ChatContent> chatContents) {
        this.chatContents = chatContents;
    }

    public void add(ChatContent chatContent) {
        chatContents.add(chatContent);
    }

    public void remove(ChatContent chatContent) {
        chatContents.remove(chatContent);
    }
}
