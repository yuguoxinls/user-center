create table if not exists yupi.tags
(
    id            bigint auto_increment comment '标签id，主键'
    primary key,
    tags_name     varchar(256)                       null comment '标签名',
    user_id       bigint                             null comment '上传标签的用户',
    parent_id     bigint                             null comment '父标签 id',
    is_parent     tinyint                            null comment '0 - 不是父标签、1 - 父标签',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 null comment '是否删除'
    )
    comment '标签';