package hyun6ik.gridgetest.infrastructure.admin;

import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.infrastructure.admin.repository.AdminQueryRepository;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdminReader {

    private final AdminQueryRepository adminQueryRepository;

    public Page<PostReportDto> getPostReportDtos(Pageable pageable) {
        return adminQueryRepository.findPostReportDtosBy(pageable);
    }

    public Page<CommentReportDto> getCommentReportDtos(Pageable pageable) {
        return adminQueryRepository.findCommentReportDtosBy(pageable);
    }

    public Page<PostDto> getPostDtos(PostStatus postStatus, String searchQuery, LocalDate searchDate, Pageable pageable) {
        return adminQueryRepository.findPostDtosBy(postStatus, searchQuery, searchDate, pageable);
    }
}
