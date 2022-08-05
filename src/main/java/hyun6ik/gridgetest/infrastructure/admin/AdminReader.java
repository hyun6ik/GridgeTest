package hyun6ik.gridgetest.infrastructure.admin;

import hyun6ik.gridgetest.infrastructure.admin.repository.AdminQueryRepository;
import hyun6ik.gridgetest.interfaces.admin.dto.PostReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminReader {

    private final AdminQueryRepository adminQueryRepository;

    public Page<PostReportDto> getBoardReportDtos(Pageable page) {
        return adminQueryRepository.findBoardReportDtosBy(page);
    }
}
