create table member
(
    id                    bigint auto_increment
        primary key,
    create_time           datetime(6)  not null,
    update_time           datetime(6)  not null,
    birth_day             date         null,
    member_condition      varchar(255) null,
    member_role           varchar(255) null,
    member_scope          varchar(255) null,
    member_type           varchar(255) null,
    refresh_token         varchar(255) null,
    token_expiration_time datetime(6)  null,
    password              varchar(255) null,
    phone_number          varchar(11)  not null,
    image                 varchar(255) null,
    introduce             varchar(255) null,
    name                  varchar(20)  not null,
    name_count            int          null,
    name_update_at        date         null,
    nick_name             varchar(20)  not null,
    nick_name_count       int          null,
    nick_name_update_at   date         null,
    web_site              varchar(255) null,
    constraint UK_f3xpkeiwuq8kwkt45lkvanwsd
        unique (nick_name)
);

create table chat_room
(
    id               bigint auto_increment
        primary key,
    create_time      datetime(6)  not null,
    update_time      datetime(6)  not null,
    chat_room_status varchar(255) null,
    last_message     varchar(200) null,
    guest_id         bigint       null,
    host_id          bigint       null,
    constraint FK4tsy5rysi7749g42bk3ywxfcu
        foreign key (host_id) references member (id),
    constraint FKrgiak3nou4wl1did0fmrfcnts
        foreign key (guest_id) references member (id)
);

create table chat
(
    id             bigint auto_increment
        primary key,
    create_time    datetime(6)  not null,
    update_time    datetime(6)  not null,
    message        varchar(200) null,
    chat_room_id   bigint       null,
    from_member_id bigint       null,
    to_member_id   bigint       null,
    constraint FK44b6elhh512d2722l09i6qdku
        foreign key (chat_room_id) references chat_room (id),
    constraint FK5gd7bskica7ov09j0rgbtp6c5
        foreign key (from_member_id) references member (id),
    constraint FKikwpxutwrqys9x13ms8lc4tbb
        foreign key (to_member_id) references member (id)
);

create table follow
(
    id            bigint auto_increment
        primary key,
    from_id       bigint       null,
    to_id         bigint       null,
    follow_status varchar(255) null,
    constraint UKmplpws9nl9qtr9gcmkcim4eit
        unique (from_id, to_id),
    constraint FKj2fgeg9m7pqcwkcyp0vrff4gc
        foreign key (to_id) references member (id),
    constraint FKppx2rbt59mr6g46v6hej8ovkd
        foreign key (from_id) references member (id)
);

create table post
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6)  not null,
    update_time datetime(6)  not null,
    content     longtext     null,
    post_status varchar(255) null,
    member_id   bigint       null,
    constraint FK83s99f4kx8oiqm3ro0sasmpww
        foreign key (member_id) references member (id)
);

create table comment
(
    id             bigint auto_increment
        primary key,
    create_time    datetime(6)  not null,
    update_time    datetime(6)  not null,
    content        longtext     not null,
    comment_status varchar(255) null,
    member_id      bigint       null,
    post_id        bigint       null,
    constraint FKmrrrpi513ssu63i2783jyiv9m
        foreign key (member_id) references member (id),
    constraint FKs1slvnkuemjsq2kj4h3vhx7i1
        foreign key (post_id) references post (id)
);

create table comment_like
(
    id         bigint auto_increment
        primary key,
    comment_id bigint null,
    member_id  bigint null,
    constraint FKjtrao5djvpcj49cxcmbenif3g
        foreign key (member_id) references member (id),
    constraint FKqlv8phl1ibeh0efv4dbn3720p
        foreign key (comment_id) references comment (id)
);

create table comment_report
(
    id            bigint auto_increment
        primary key,
    report_reason varchar(255) null,
    comment_id    bigint       null,
    member_id     bigint       null,
    constraint FK1yteay3x7j2g5us0yrukwryk
        foreign key (member_id) references member (id),
    constraint FK8ugevhla12t9n0uw4o0rkvnth
        foreign key (comment_id) references comment (id)
);

create table image
(
    id           bigint auto_increment
        primary key,
    create_time  datetime(6)  not null,
    update_time  datetime(6)  not null,
    is_rep_image bit          null,
    url          varchar(255) not null,
    post_id      bigint       null,
    constraint FKe2l07hc93u2bbjnl80meu3rn4
        foreign key (post_id) references post (id)
);

create table likes
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6) not null,
    update_time datetime(6) not null,
    member_id   bigint      null,
    post_id     bigint      null,
    constraint FKa4vkf1skcfu5r6o5gfb5jf295
        foreign key (member_id) references member (id),
    constraint FKowd6f4s7x9f3w50pvlo6x3b41
        foreign key (post_id) references post (id)
);

create table post_report
(
    id            bigint auto_increment
        primary key,
    report_reason varchar(255) null,
    member_id     bigint       null,
    post_id       bigint       null,
    constraint FK1ophlvcx2lasp1shw07qheefc
        foreign key (member_id) references member (id),
    constraint FKeyehd7v09u9oxijrfvw1ufof
        foreign key (post_id) references post (id)
);

