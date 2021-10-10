create table mainboard(
    boardNum int,
    boardTitle varchar2(50) not null,
    boardContent varchar2(3000) not null,
    memberid varchar2(50) not null,
    boardDate date not null,
    boardFile varchar2(500),
    boardAvailable int,
    boardReadCount int default 0,
    primary key(boardNum)
)