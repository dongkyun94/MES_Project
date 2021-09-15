create table member (
   memberid VARCHAR2(50) primary key,
   name VARCHAR2(50) not null,
   password VARCHAR2(10) not null,
   regdate date not null);