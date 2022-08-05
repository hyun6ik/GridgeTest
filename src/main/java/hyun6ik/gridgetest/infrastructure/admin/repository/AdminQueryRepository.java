package hyun6ik.gridgetest.infrastructure.admin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.interfaces.admin.dto.response.CommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.response.PostReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.QCommentReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.QPostReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hyun6ik.gridgetest.domain.comment.entity.QComment.*;
import static hyun6ik.gridgetest.domain.comment.report.QCommentReport.*;
import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;
import static hyun6ik.gridgetest.domain.post.report.QPostReport.*;

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
}
