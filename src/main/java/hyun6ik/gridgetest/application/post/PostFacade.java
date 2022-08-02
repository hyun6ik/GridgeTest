package hyun6ik.gridgetest.application.post;

import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.service.MemberService;
import hyun6ik.gridgetest.domain.post.Post;
import hyun6ik.gridgetest.domain.post.report.constant.ReportReason;
import hyun6ik.gridgetest.domain.post.service.PostService;
import hyun6ik.gridgetest.interfaces.common.dto.LikeDto;
import hyun6ik.gridgetest.interfaces.post.dto.PostDtoAssembler;
import hyun6ik.gridgetest.interfaces.common.dto.ReportDto;
import hyun6ik.gridgetest.interfaces.post.dto.request.PostRegisterRequestDto;
import hyun6ik.gridgetest.interfaces.post.dto.response.PostRegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostFacade {

    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public PostRegisterResponseDto createPost(Long memberId, PostRegisterRequestDto request) {
        final Member member = memberService.getMemberBy(memberId);
        final Post initPost = PostDtoAssembler.toEntity(member, request);
        return postService.createPost(initPost);
    }

    @Transactional
    public LikeDto likePost(Long memberId, Long postId) {
        final Member member = memberService.getMemberBy(memberId);
        final Post post = postService.getPostBy(postId);
        post.like(member);
        return new LikeDto(post.getLikeCounts(), true);
    }

    @Transactional
    public LikeDto unlikePost(Long memberId, Long postId) {
        final Member member = memberService.getMemberBy(memberId);
        final Post post = postService.getPostBy(postId);
        post.unlike(member);
        return new LikeDto(post.getLikeCounts(), false);
    }

    @Transactional
    public ReportDto.Response reportPost(Long memberId, Long postId, ReportReason reportReason) {
        final Member member = memberService.getMemberBy(memberId);
        final Post post = postService.getPostBy(postId);
        post.report(member, reportReason);
        return new ReportDto.Response(post.getReportCounts());
    }
}
