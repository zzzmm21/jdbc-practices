-- emaillist sql 연습

-- insert
insert into emaillist values(null, '안', '대혁', 'kickscar@gmail.com');
insert into emaillist values(null, '둘', '리', 'dooly@gmail.com');

-- select
select * from emaillist order by no desc;

-- delete(by email)
delete from emaillist where email = 'kickscar@gmail.com';

desc emaillist;

select * from dept;

insert
  into dept
values (null, '디자인');

delete
  from dept
 where no = 8;
 
 update dept
    set name = '전략기획'
  where no = 4;