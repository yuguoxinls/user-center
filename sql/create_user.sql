create table if not exists yupi.user
(
    id            bigint auto_increment comment '用户id，主键'
    primary key,
    user_name     varchar(256)                       null comment '昵称',
    user_account  varchar(256)                       null comment '登录账号',
    avatar_url    varchar(1024)                      null comment '头像',
    gender        tinyint                            null comment '性别',
    user_password varchar(256)                       null comment '密码',
    phone         varchar(512)                       null comment '电话',
    email         varchar(1024)                      null comment '邮箱',
    user_status   int      default 0                 null comment '用户状态',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 null comment '是否删除',
    planet_code   varchar(255)                       not null comment '星球编号',
    user_role     tinyint  default 0                 not null comment '用户角色',
    tags          varchar(1024)                      null comment '用户标签列表'
    )
    comment '用户';