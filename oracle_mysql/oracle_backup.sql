create table team(
 teamid number(2) primary key,
 teamname varchar2(20) not null
);

create table schedule(
 scheduleid number(4) primary key,
 matchdate date not null,
 matchtime varchar2(5) not null,
 hometeamid number(2) not null,
 awayteamid number(2) not null,
 result varchar2(5) not null,
 address varchar2(20) not null,
 count number(4) not null
);

create sequence seq_teamid
increment by 1
start with 1;

create sequence seq_scheduleid
increment by 1
start with 1;

alter table schedule add constraints fk_hometeamid foreign key(hometeamid)
references team(teamid);

alter table schedule add constraints fk_awayteamid foreign key(awayteamid)
references team(teamid);

insert into team(teamid, teamname) values(seq_teamid.nextval, '北京国安');
insert into team(teamid, teamname) values(seq_teamid.nextval, '长春亚泰');
insert into team(teamid, teamname) values(seq_teamid.nextval, '广州恒大');
insert into team(teamid, teamname) values(seq_teamid.nextval, '山东鲁能');

insert into schedule values(seq_scheduleid.nextval,sysdate,'15:00',1,2,'3:1','北京工体',20);
insert into schedule values(seq_scheduleid.nextval,sysdate,'16:00',1,3,'2:1','北京工体',21);
insert into schedule values(seq_scheduleid.nextval,sysdate,'17:00',1,4,'3:4','北京工体',22);
insert into schedule values(seq_scheduleid.nextval,sysdate+1,'15:00',2,3,'0:1','长春奥体中心',23);
insert into schedule values(seq_scheduleid.nextval,sysdate+1,'16:00',2,4,'2:1','长春奥体中心',24);
insert into schedule values(seq_scheduleid.nextval,sysdate+2,'15:00',3,4,'0:1','广州工体',25);
commit;

