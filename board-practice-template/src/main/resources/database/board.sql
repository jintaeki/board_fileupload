drop table board;

create table board (
  bno number primary key,
  btitle varchar(200) not null,
  bcontent varchar(4000) not null,
  bdate date not null,
  bwriter varchar(20) not null,
  bhitcount number(5) not null,
  battachoname varchar(100) null,
  battachsname varchar(100) null,
  battachtype varchar(50) null
);

drop sequence seq_bno;
create sequence seq_bno;

insert into board (bno, btitle, bcontent, bdate, bwriter, bhitcount) 
values (seq_bno.nextval, 'Spring', 'Spring을 이용한 MVC 웹 애플리케이션', sysdate, 'winter', 0);
commit;

