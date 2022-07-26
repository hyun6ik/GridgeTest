package hyun6ik.gridgetest.infrastructure.member.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.constant.MemberScope;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.follow.Follow;
import hyun6ik.gridgetest.domain.member.follow.constant.FollowStatus;
import hyun6ik.gridgetest.domain.post.constant.PostStatus;
import hyun6ik.gridgetest.interfaces.member.dto.MyPageDto;
import hyun6ik.gridgetest.interfaces.member.dto.QMyPageDto;
import hyun6ik.gridgetest.interfaces.member.dto.QMyPageDto_PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hyun6ik.gridgetest.domain.member.entity.QMember.*;
import static hyun6ik.gridgetest.domain.member.follow.QFollow.*;
import static hyun6ik.gridgetest.domain.post.QPost.*;
import static hyun6ik.gridgetest.domain.post.image.QImage.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Member> findSocialMemberBy(SocialUserInfo socialUserInfo) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.profile.nickName.eq(socialUserInfo.getName()),
                        member.memberStatus.memberType.eq(socialUserInfo.getMemberType()),
                        member.memberStatus.memberCondition.ne(MemberCondition.RESIGNED))
                .fetchOne());

    }

    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(member)
                        .where(member.id.eq(memberId), member.memberStatus.memberCondition.ne(MemberCondition.RESIGNED))
                        .fetchOne()
        );
    }

    public Optional<MyPageDto> findMyPageDtoBy(Long memberId) {
        return Optional.ofNullable(
                queryFactory
                        .select(new QMyPageDto(
                                member.profile.nickName,
                                member.profile.image,
                                member.profile.name,
                                member.posts.posts.size(),
                                getFollowerCount(memberId),
                                getFollowingCount(memberId)
                        ))
                        .from(member)
                        .where(member.id.eq(memberId), member.memberStatus.memberCondition.eq(MemberCondition.NORMAL))
                        .fetchOne()
        );
    }

    public Page<MyPageDto.PostDto> findMyPagePostDtosBy(Long memberId, Pageable pageable) {
        final List<MyPageDto.PostDto> content = queryFactory
                .select(new QMyPageDto_PostDto(
                        post.id,
                        image.url
                ))
                .distinct()
                .from(image)
                .innerJoin(image.post, post)
                .innerJoin(image.post.member, member)
                .where(post.postStatus.eq(PostStatus.USE), member.id.eq(memberId), image.isRepImage.isTrue())
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final int size = queryFactory
                .selectFrom(post)
                .innerJoin(post.member, member)
                .where(post.postStatus.eq(PostStatus.USE), post.member.id.eq(memberId))
                .fetch()
                .size();
        return new PageImpl<>(content, pageable, size);
    }

    private JPQLQuery<Long> getFollowingCount(Long memberId) {
        return JPAExpressions
                .select(follow.count())
                .from(follow)
                .innerJoin(follow.from, member)
                .where(follow.from.id.eq(memberId), follow.followStatus.eq(FollowStatus.APPROVED));
    }

    private JPQLQuery<Long> getFollowerCount(Long memberId) {
        return JPAExpressions
                .select(follow.count())
                .from(follow)
                .innerJoin(follow.to, member)
                .where(follow.to.id.eq(memberId), follow.followStatus.eq(FollowStatus.APPROVED));
    }

    public Optional<Follow> findPrivateMemberWithFollowBy(Long followId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(follow)
                        .where(follow.id.eq(followId))
                        .fetchOne()
        );
    }
}
