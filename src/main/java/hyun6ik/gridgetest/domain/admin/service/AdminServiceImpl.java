package hyun6ik.gridgetest.domain.admin.service;

import hyun6ik.gridgetest.infrastructure.admin.AdminReader;
import hyun6ik.gridgetest.interfaces.admin.dto.request.MemberSearchDto;
import hyun6ik.gridgetest.interfaces.admin.dto.request.PostSearchDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.MemberDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
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
    public Page<PostReportDto> getPostReportDtos(Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return adminReader.getPostReportDtos(pageable);
    }

    @Override
    public Page<CommentReportDto> getCommentReportDtos(Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return adminReader.getCommentReportDtos(pageable);
    }

    @Override
    public Page<PostDto> getPostDtos(PostSearchDto request, Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return adminReader.getPostDtos(request.getPostStatus(), request.getSearchQuery(), request.getSearchDate(), pageable);
    }

    @Override
    public PostDto getPostDto(Long postId) {
        return adminReader.getPostDto(postId);
    }

    @Override
    public Page<MemberDto> getMemberDtos(MemberSearchDto request, Optional<Integer> page) {
        final Pageable pageable = PageRequest.of(page.orElse(0), 10);
        return adminReader.getMemberDtos(request.getMemberCondition(), request.getSearchName(), request.getSearchNickName(), request.getSearchDate(), pageable);
    }
}
