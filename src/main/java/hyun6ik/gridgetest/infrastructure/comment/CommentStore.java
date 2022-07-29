package hyun6ik.gridgetest.infrastructure.comment;

import hyun6ik.gridgetest.infrastructure.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentStore {

    private final CommentRepository commentRepository;
}
