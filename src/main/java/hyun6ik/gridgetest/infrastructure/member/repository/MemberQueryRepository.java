package hyun6ik.gridgetest.infrastructure.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.login.vo.SocialUserInfo;
import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hyun6ik.gridgetest.domain.member.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Member> findSocialMemberBy(SocialUserInfo socialUserInfo) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.profile.name.eq(socialUserInfo.getName()),
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
}
