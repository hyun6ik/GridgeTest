package hyun6ik.gridgetest.domain.admin.service;

import hyun6ik.gridgetest.interfaces.admin.dto.PostReportDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AdminService {
    Page<PostReportDto> getPostReportDtos(Optional<Integer> page);
}
