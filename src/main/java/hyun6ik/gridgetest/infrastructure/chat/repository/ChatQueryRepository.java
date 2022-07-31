package hyun6ik.gridgetest.infrastructure.chat.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.chat.constant.ChatRoomStatus;
import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hyun6ik.gridgetest.domain.chat.entity.QChatRoom.*;
import static hyun6ik.gridgetest.domain.member.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class ChatQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ChatRoom> findBy(Member host, Member guest) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(chatRoom)
                        .innerJoin(chatRoom.host, member)
                        .innerJoin(chatRoom.guest, member)
                        .where(chatRoom.host.eq(host), chatRoom.guest.eq(guest), chatRoom.chatRoomStatus.eq(ChatRoomStatus.USE))
                        .fetchOne()
        );
    }
}
