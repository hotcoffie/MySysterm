/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/16 0:58:43                            */
/*==============================================================*/


drop table if exists base_code;

drop table if exists base_code_type;

drop table if exists base_company;

drop table if exists base_dept;

drop table if exists base_logs;

drop table if exists base_user;

drop table if exists work_activity;

drop table if exists work_attachment;

drop table if exists work_comp_act_re;

drop table if exists work_dept_act_re;

drop table if exists work_user_act_re;

/*==============================================================*/
/* Table: base_code                                             */
/*==============================================================*/
create table base_code
(
   ID                   int not null auto_increment comment 'ID',
   code_type_id         int not null comment '字典分类',
   name                 varchar(50) not null comment '名称',
   order_index          int not null default 1 comment '排序',
   value_1              varchar(100) comment '值1,长度100',
   value_2              varchar(100) comment '值2,长度100',
   value_3              varchar(100) comment '值3,长度100',
   value_4              varchar(500) comment '值4,长度500',
   value_5              varchar(500) comment '值5,长度500',
   remark               varchar(500) comment '备注',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (ID)
);

alter table base_code comment '数据字典';

/*==============================================================*/
/* Table: base_code_type                                        */
/*==============================================================*/
create table base_code_type
(
   id                   int not null auto_increment comment 'ID',
   code_type_name       varchar(100) not null comment '名称',
   cid                  varchar(100) not null comment '编码',
   pid                  int not null default -1 comment '父节点',
   order_index          int not null default 1 comment '排序',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table base_code_type comment '字典分类';

/*==============================================================*/
/* Table: base_company                                          */
/*==============================================================*/
create table base_company
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(200) not null comment '企业名称',
   legal_representative varchar(50) comment '企业法人名称',
   finance_chief        varchar(50) comment '财务负责人名称',
   `tax collector_user_id` int comment '专管员ID',
   dept_id              int comment '部门ID',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table base_company comment '企业信息';

/*==============================================================*/
/* Table: base_dept                                             */
/*==============================================================*/
create table base_dept
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(20) not null comment '部门名称',
   cid                  varchar(100) not null comment '节点编码,用于生成树结构',
   pid                  int not null default -1 comment '父节点ID',
   master_id            int comment '部门负责人ID',
   leader_id            int comment '部门领导ID',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table base_dept comment '部门';

/*==============================================================*/
/* Table: base_logs                                             */
/*==============================================================*/
create table base_logs
(
   id                   int not null auto_increment comment 'ID',
   log_type             int not null default 0 comment '日志类型(枚举)
            0. 无
            1. 增
            2. 删
            3. 改
            4. 查',
   `desc`               varchar(500) comment '日志描述',
   operator_id          int not null comment '操作人ID',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '操作时间',
   primary key (id)
);

alter table base_logs comment '系统日志';

/*==============================================================*/
/* Table: base_user                                             */
/*==============================================================*/
create table base_user
(
   id                   int not null auto_increment comment 'ID',
   account              varchar(10) not null comment '账号',
   password             varchar(32) not null comment '密码',
   name                 varchar(10) comment '姓名',
   dept_id              int comment '所属部门ID',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table base_user comment '用户';

/*==============================================================*/
/* Table: work_activity                                         */
/*==============================================================*/
create table work_activity
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(100) comment '活动名称',
   open_level           int(1) default 1 comment '开放级别(枚举)
            1.仅对自己开放
            2.仅对本部门开放
            3.仅对本部门及所属上级部门开放
            4.对所有人开放',
   desc_id              varchar(50) comment '活动描述MongoDB的ID',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table work_activity comment '活动';

/*==============================================================*/
/* Table: work_attachment                                       */
/*==============================================================*/
create table work_attachment
(
   id                   int not null auto_increment comment 'ID',
   activity_id          int comment '所属活动ID',
   file_type_id         char(1) not null default '3' comment '文件类型ID(字典)
            1.文档
            2.图片
            3.其他',
   file_name            varchar(100) not null comment '名称',
   file_id              varchar(50) not null comment 'MongDB的UUID',
   file_size            int not null comment '文件大小',
   add_time             datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   last_time            datetime default CURRENT_TIMESTAMP comment '最后修改时间',
   add_user_id          int not null comment '创建人ID',
   primary key (id)
);

alter table work_attachment comment '附件';

/*==============================================================*/
/* Table: work_comp_act_re                                      */
/*==============================================================*/
create table work_comp_act_re
(
   ID                   int not null auto_increment comment 'ID',
   activity_id          int not null comment '活动ID',
   company_id           int not null comment '企业ID',
   primary key (ID)
);

alter table work_comp_act_re comment '企业活动关系表';

/*==============================================================*/
/* Table: work_dept_act_re                                      */
/*==============================================================*/
create table work_dept_act_re
(
   ID                   int not null auto_increment comment 'ID',
   activity_id          int not null comment '活动ID',
   dept_id              int not null comment '部门ID',
   primary key (ID)
);

alter table work_dept_act_re comment '部门活动关系表';

/*==============================================================*/
/* Table: work_user_act_re                                      */
/*==============================================================*/
create table work_user_act_re
(
   ID                   int not null auto_increment comment 'ID',
   activity_id          int not null comment '活动ID',
   user_id              int not null comment '用户ID',
   primary key (ID)
);

alter table work_user_act_re comment '用户活动关系表';

