create table student
(
    id   int auto_increment,
    oaid int          not null,
    name varchar(256) null,
    constraint student_pk
        primary key (id)
);

create unique index student_name_uindex
    on student (name);

create table exam
(
    id        int auto_increment,
    name      varchar(256) null,
    begintime datetime     null,
    endtime   datetime     null,
    online    boolean      null,
    studentid int          null,
    constraint exam_pk1
        primary key (id)
);

create table examalreadyexists
(
    id        int auto_increment,
    name      varchar(256) null,
    begintime datetime     null,
    endtime   datetime     null,
    online    boolean      null,
    lsfid     int          null,
    constraint exam_pk2
        primary key (id)
);

create table holiday
(
    id        int auto_increment,
    begintime datetime null,
    endtime   datetime null,
    studentid int      null,
    constraint holiday_pk
        primary key (id)
);

create table log
(
    id        int auto_increment,
    type      varchar(256) null,
    time      datetime     null,
    studentid int          null,
    name      varchar(256) null,
    action    varchar(256) null,
    constraint log_pk
        primary key (id)
);








