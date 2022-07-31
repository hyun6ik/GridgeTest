package hyun6ik.gridgetest.application.chat;

import hyun6ik.gridgetest.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatService chatService;
}
