create database SurveyWeb
go
use SurveyWeb
go
create table ManagerDetail 
(
   managerId            bigint                         not null				identity(1,1),
   username             varchar(50)                    null,
   password             varchar(200)                   null,
   name                 varchar(50)                    null,
   gender               int                            null,
   birthYear            date                           null,
   nickname             varchar(50)                    null,
   mobile               numeric(11)                    null,
   email                varchar(50)                    null,
   constraint PK_MANAGERDETAIL primary key clustered (managerId)
);
insert into ManagerDetail values('a','c','d',1,GETDATE(),'y',12345678910,'e')
create table UserDetail 
(
   userId               bigint                         not null				identity(1,1),
   Username             varchar(50)                    null,
   Password             varchar(200)                   null,
   Name                 varchar(50)                    null,
   gender               int                            null,
   birthYear            date                           null,
   nickname             varchar(50)                    null,
   mobile               numeric(11)                    null,
   email                varchar(50)                    null,
   constraint PK_USERDETAIL primary key clustered (userId)
);
create table Batch 
(
   batchId              bigint                         not null				identity(1,1),
   batchName            varchar(50)                    null,
   description          text                           null,
   createTime           datetime                       null,
   managerId            bigint                         null,
   constraint PK_BATCH primary key clustered (batchId)
);

create table Participation 
(
   partId               bigint                         not null				identity(1,1),
   batchId              bigint                         null,
   userId               bigint                         null,
   partTime             datetime                       null,
   constraint PK_PARTICIPATION primary key clustered (partId)
);

create table Tip 
(
   tipId                bigint                         not null				identity(1,1),
   managerId            bigint                         null,
   batchId              bigint                         null,
   tips                 varchar(200)                   null,
   constraint PK_TIP primary key clustered (tipId)
);

create table Survey 
(
   naireId              bigint                         not null				identity(1,1),
   naireName            text                           null,
   description          text                           null,
   createTime           datetime                       null,
   batchId              bigint                         null,
   constraint PK_SUREVY primary key clustered (naireId)
);

create table Answer 
(
   answerId             bigint                         not null				identity(1,1),
   userId               bigint                         null,
   naireId              bigint                         null,
   answerTime           datetime                       null,
   constraint PK_ANSWER primary key clustered (answerId)
);

create table feedback 
(
   feedbackId           bigint                         not null				identity(1,1),
   userId               bigint                         null,
   naireId              bigint                         null,
   feedbacks            varchar(200)                   null,
   constraint PK_FEEDBACK primary key clustered (feedbackId)
);

create table Question 
(
   questionId           bigint                         not null				identity(1,1),
   type                 int                            null,
   "order"              bigint                         null,
   content              text                           null,
   naireId              bigint                         null,
   constraint PK_QUESTION primary key clustered (questionId)
);

create table "Option" 
(
   optionId             bigint                         not null				identity(1,1),
   content              text                           null,
   questionId           bigint                         null,
   constraint PK_OPTION primary key clustered (optionId)
);
