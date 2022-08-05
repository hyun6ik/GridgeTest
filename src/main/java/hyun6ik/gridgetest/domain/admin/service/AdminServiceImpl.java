package hyun6ik.gridgetest.domain.admin.service;

import hyun6ik.gridgetest.infrastructure.admin.AdminReader;
import hyun6ik.gridgetest.interfaces.admin.dto.PostReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminReader adminReader;

    @Override
    public Page<PostReportDto> getBoardReportDtos(Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return adminReader.getBoardReportDtos(pageable);
    }
}
