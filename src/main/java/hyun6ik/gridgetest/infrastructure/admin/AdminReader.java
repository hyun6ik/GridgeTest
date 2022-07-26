package hyun6ik.gridgetest.infrastructure.admin;

import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.NotFoundException;
import hyun6ik.gridgetest.infrastructure.admin.repository.AdminQueryRepository;
import hyun6ik.gridgetest.interfaces.admin.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

    public PostDto getPostDto(Long postId) {
        final PostDto postDto = adminQueryRepository.findPostDtoBy(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_POST));
        postDto.addPostLikeDtos(adminQueryRepository.findPostLikeDtosBy(postId));
        postDto.addPostReportDtos(adminQueryRepository.findPostReportDtosBy(postId));
        postDto.addCommentDtos(adminQueryRepository.findCommentDtosBy(postId));
        return postDto;
    }

    public Page<MemberDto> getMemberDtos(MemberCondition memberCondition, String searchName, String searchNickName, LocalDate searchDate, Pageable pageable) {
        return adminQueryRepository.findMemberDtosBy(memberCondition, searchName, searchNickName, searchDate, pageable);
    }
}
