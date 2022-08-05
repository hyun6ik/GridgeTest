package hyun6ik.gridgetest.domain.admin.service;

import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AdminService {
    Page<PostReportDto> getPostReportDtos(Optional<Integer> page);

    Page<CommentReportDto> getCommentReportDtos(Optional<Integer> page);
}
