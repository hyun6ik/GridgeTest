package hyun6ik.gridgetest.infrastructure.admin.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.comment.entity.QComment;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.domain.post.like.QLike;
import hyun6ik.gridgetest.interfaces.admin.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static hyun6ik.gridgetest.domain.comment.entity.QComment.comment;
import static hyun6ik.gridgetest.domain.comment.report.QCommentReport.commentReport;
import static hyun6ik.gridgetest.domain.member.entity.QMember.member;
import static hyun6ik.gridgetest.domain.post.QPost.post;
import static hyun6ik.gridgetest.domain.post.like.QLike.*;
import static hyun6ik.gridgetest.domain.post.report.QPostReport.postReport;


@Repository
@RequiredArgsConstructor
public class AdminQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<PostReportDto> findPostReportDtosBy(Pageable pageable) {
        final List<PostReportDto> content = queryFactory
                .select(new QPostReportDto(
                        postReport.id,
                        post.id,
                        member.profile.nickName,
                        postReport.createTime,
                        postReport.reportReason,
                        post.postStatus
                ))
                .from(postReport)
                .innerJoin(postReport.post, post)
                .innerJoin(postReport.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(postReport.createTime.desc())
                .fetch();

        final int size = queryFactory
                .select(postReport.count())
                .from(postReport)
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, size);
    }

    public Page<CommentReportDto> findCommentReportDtosBy(Pageable pageable) {
        final List<CommentReportDto> content = queryFactory
                .select(new QCommentReportDto(
                        commentReport.id,
                        comment.id,
                        member.profile.nickName,
                        commentReport.createTime,
                        commentReport.reportReason,
                        comment.commentStatus
                ))
                .from(commentReport)
                .innerJoin(commentReport.comment, comment)
                .innerJoin(commentReport.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(commentReport.createTime.desc())
                .fetch();

        final int size = queryFactory
                .select(commentReport.count())
                .from(commentReport)
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, size);
    }

    public Page<PostDto> findPostDtosBy(PostStatus postStatus, String searchQuery, LocalDate searchDate, Pageable pageable) {
        final List<PostDto> content = queryFactory
                .select(new QPostDto(
                        post.id,
                        member.id,
                        member.profile.nickName,
                        post.postContent.content,
                        post.postStatus,
                        post.createTime,
                        post.updateTime
                ))
                .from(post)
                .innerJoin(post.member, member)
                .where(containPostStatus(postStatus), containNickName(searchQuery), betweenSearchDate(searchDate))
                .orderBy(post.createTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final int size = queryFactory
                .select(post.count())
                .from(post)
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, size);
    }

    private BooleanExpression containPostStatus(PostStatus postStatus) {
        return postStatus == null ? null : post.postStatus.eq(postStatus);
    }

    private BooleanExpression containNickName(String searchQuery) {
        return StringUtils.isBlank(searchQuery) ? null : post.member.profile.nickName.containsIgnoreCase(searchQuery);
    }

    private BooleanExpression betweenSearchDate(LocalDate searchDate) {
        if (searchDate == null) {
            return null;
        }
        final LocalDateTime startDate = searchDate.atStartOfDay();
        final LocalDateTime endDate = LocalDateTime.of(searchDate, LocalTime.MAX).withNano(0);
        return post.createTime.between(startDate, endDate);
    }


    public Optional<PostDto> findPostDtoBy(Long postId) {
        return Optional.ofNullable(
                queryFactory
                        .select(new QPostDto(
                                post.id,
                                member.id,
                                member.profile.nickName,
                                post.postContent.content,
                                post.postStatus,
                                post.createTime,
                                post.updateTime
                        ))
                        .from(post)
                        .innerJoin(post.member, member)
                        .where(post.id.eq(postId))
                        .orderBy(post.createTime.desc())
                        .fetchOne()
        );
    }

    public List<PostLikeDto> findPostLikeDtosBy(Long postId) {
        return queryFactory
                .select(new QPostLikeDto(
                        like.id,
                        post.id,
                        member.id,
                        like.createTime,
                        like.updateTime
                ))
                .from(like)
                .innerJoin(like.post, post)
                .innerJoin(like.member, member)
                .where(like.post.id.eq(postId))
                .orderBy(like.createTime.desc())
                .fetch();
    }

    public List<PostReportDto> findPostReportDtosBy(Long postId) {
        return queryFactory
                .select(new QPostReportDto(
                        postReport.id,
                        post.id,
                        member.profile.nickName,
                        postReport.createTime,
                        postReport.reportReason,
                        post.postStatus
                ))
                .from(postReport)
                .innerJoin(postReport.post, post)
                .innerJoin(postReport.member, member)
                .where(postReport.post.id.eq(postId))
                .orderBy(postReport.createTime.desc())
                .fetch();
    }

    public List<CommentDto> findCommentDtosBy(Long postId) {
        return queryFactory
                .select(new QCommentDto(
                        comment.id,
                        post.id,
                        member.id,
                        member.profile.nickName,
                        comment.commentContent.content,
                        comment.commentStatus,
                        post.comments.comments.size(),
                        comment.commentLikes.commentLikes.size(),
                        comment.createTime,
                        comment.updateTime
                ))
                .from(comment)
                .innerJoin(comment.post, post)
                .innerJoin(comment.member, member)
                .where(comment.post.id.eq(postId))
                .fetch();
    }
}
