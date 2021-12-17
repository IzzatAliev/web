drop table if exists student_course;
drop table if exists management_course;
drop table if exists students;
drop table if exists courses;
drop table if exists managements;

create table students
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    visible    bit null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    age       int   not null
);

create table courses
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    visible          bit          null,
    course_name        varchar(255) not null,
    credit          int     not null,
    course_type     varchar(255) not null,
    description      text         null
);

create table managements
(
    id      bigint auto_increment
        primary key,
    created datetime(6)  null,
    updated datetime(6)  null,
    visible bit          null,
    management_name    varchar(255) not null,
    staff_count     int  not null
);

create table student_course
(
    student_id bigint not null,
    course_id   bigint not null,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id) on delete cascade,
    foreign key (course_id) references courses (id)
);

create table management_course
(
    management_id bigint not null,
    course_id      bigint not null,
    primary key (management_id, course_id),
    foreign key (course_id) references courses (id),
    foreign key (management_id) references managements (id)
);
