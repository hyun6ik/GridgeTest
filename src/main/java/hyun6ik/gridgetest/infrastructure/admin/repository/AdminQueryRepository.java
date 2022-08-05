package hyun6ik.gridgetest.infrastructure.admin.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.member.entity.QMember;
import hyun6ik.gridgetest.domain.post.QPost;
import hyun6ik.gridgetest.domain.post.report.QPostReport;
import hyun6ik.gridgetest.interfaces.admin.dto.PostReportDto;
import hyun6ik.gridgetest.interfaces.admin.dto.QPostReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;
import static hyun6ik.gridgetest.domain.post.report.QPostReport.*;

@Repository
@RequiredArgsConstructor
public class AdminQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<PostReportDto> findBoardReportDtosBy(Pageable pageable) {
        final List<PostReportDto> content = queryFactory
                .select(new QPostReportDto(
                        postReport.id,
                        post.id,
                        member.profile.nickName,
                        postReport.createTime,
                        postReport.reportReason
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
}
