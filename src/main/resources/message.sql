create database message;
use message;


create table User
(
    uid      int         not null primary key auto_increment,
    userName varchar(20) not null unique ,
    nickName nvarchar(100) not null,
    password varchar(20) not null,
    email  varchar(30) not null unique ,
    avatar varchar(20) not null
);

create table Comment
(
    cid int not null primary key auto_increment,
    uid int not null ,
    createTime datetime not null,
    content nvarchar(500) not null,
    parentCommentId int default null,
    replyCommentId int  default null,
    email  varchar(30) not null,
    userName nvarchar(20) not null,
    remind bool default false,
    constraint foreign key (uid) references User(uid) ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (parentCommentId) references Comment(cid)   ON DELETE CASCADE ON UPDATE RESTRICT,
    constraint foreign key (replyCommentId) references Comment(cid)  ON DELETE CASCADE ON UPDATE RESTRICT

);
