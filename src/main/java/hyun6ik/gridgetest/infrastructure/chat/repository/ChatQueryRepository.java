package hyun6ik.gridgetest.infrastructure.chat.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hyun6ik.gridgetest.domain.chat.constant.ChatRoomStatus;
import hyun6ik.gridgetest.domain.chat.entity.ChatRoom;
import hyun6ik.gridgetest.domain.chat.entity.QChat;
import hyun6ik.gridgetest.domain.member.constant.MemberCondition;
import hyun6ik.gridgetest.domain.member.entity.Member;
import hyun6ik.gridgetest.interfaces.chat.dto.ChatRoomDto;
import hyun6ik.gridgetest.interfaces.chat.dto.QChatRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hyun6ik.gridgetest.domain.chat.entity.QChat.*;
import static hyun6ik.gridgetest.domain.chat.entity.QChatRoom.*;
import static hyun6ik.gridgetest.domain.member.entity.QMember.*;

@Repository
@RequiredArgsConstructor
public class ChatQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ChatRoom> findBy(Member host, Member guest) {
        final List<Member> members = List.of(host, guest);
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(chatRoom)
                        .innerJoin(chatRoom.host, member)
                        .fetchJoin()
                        .innerJoin(chatRoom.guest, member)
                        .fetchJoin()
                        .where(chatRoom.host.in(members), chatRoom.guest.in(members), chatRoom.chatRoomStatus.eq(ChatRoomStatus.USE))
                        .fetchOne()
        );
    }

    public List<ChatRoomDto> findChatRoomDtosBy(Long memberId, Long chatRoomId) {
        return queryFactory
                .select(new QChatRoomDto(
                        chat.from.id,
                        chat.from.profile.image,
                        chat.message,
                        chat.createTime
                ))
                .from(chat)
                .innerJoin(chat.from, member)
                .innerJoin(chat.chatRoom, chatRoom)
                .where(chat.chatRoom.id.eq(chatRoomId), chat.chatRoom.host.id.eq(memberId).or(chat.chatRoom.guest.id.eq(memberId)))
                .fetch();
    }
}
