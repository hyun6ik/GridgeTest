package hyun6ik.gridgetest.domain.member.follow;

import hyun6ik.gridgetest.domain.member.constant.MemberScope;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.domain.member.follow.constant.FollowStatus;
import hyun6ik.gridgetest.global.error.exception.ErrorCode;
import hyun6ik.gridgetest.global.error.exception.FollowException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"from_id", "to_id"})
        }
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private Member from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private Member to;

    @Enumerated(EnumType.STRING)
    private FollowStatus followStatus;

    public Follow(Member from, Member to) {
        validateDifferentSourceTarget(from, to);
        this.from = from;
        this.to = to;
        this.followStatus = targetMemberScopeIsPublic(to) ? FollowStatus.APPROVED : FollowStatus.PENDING;
    }

    private Boolean targetMemberScopeIsPublic(Member to) {
        return to.getMemberStatus().getMemberScope().equals(MemberScope.PUBLIC);
    }


    private void validateDifferentSourceTarget(Member from, Member to) {
        if (from.equals(to)) {
            throw new FollowException(ErrorCode.CANNOT_FOLLOW_MYSELF);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Follow follow = (Follow) o;
        return Objects.equals(from, follow.getFrom())
                && Objects.equals(to, follow.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
