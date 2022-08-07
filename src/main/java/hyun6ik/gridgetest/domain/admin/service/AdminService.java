package hyun6ik.gridgetest.domain.admin.service;

import hyun6ik.gridgetest.interfaces.admin.dto.request.MemberSearchDto;
import hyun6ik.gridgetest.interfaces.admin.dto.request.PostSearchDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.MemberDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AdminService {
    Page<PostReportDto> getPostReportDtos(Optional<Integer> page);

    Page<CommentReportDto> getCommentReportDtos(Optional<Integer> page);

    Page<PostDto> getPostDtos(PostSearchDto request, Optional<Integer> page);

    PostDto getPostDto(Long postId);

    Page<MemberDto> getMemberDtos(MemberSearchDto request, Optional<Integer> page);
}
