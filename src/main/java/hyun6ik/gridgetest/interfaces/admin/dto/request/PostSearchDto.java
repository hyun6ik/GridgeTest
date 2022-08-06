package hyun6ik.gridgetest.interfaces.admin.dto.request;

import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PostSearchDto {

    private String searchQuery = "";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate searchDate;
    private PostStatus postStatus;
}
