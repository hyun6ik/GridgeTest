alter table comment_report
    add column create_time datetime(6) not null

alter table post_report
    add column create_time datetime(6) not null