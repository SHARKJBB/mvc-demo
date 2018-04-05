create database gzhb;
use gzhb;
show tables;
drop table user;
#�û���
create table user(
	id int auto_increment primary key,
	gh varchar(20),#���ţ���ע���ʱ���Զ���������ǰ���ڼ�id
	name varchar(50),
	pwd varchar(50),
	phone varchar(20),
	pow int default 0,#Ȩ�ޣ�0:��ʾ��ͨԱ����1:��ʾ����Ա��2:��ʾ��������Ա
	manager_id int default 0,#0:��ʾû���ϼ����������˾����������Ա�û���
	bm_id int#����ID��
);
#��������Ա�û�
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711151","a","201711151","12345678901",2,0,1);
#����Ա�û�
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711152","b","201711152","12345678901",1,1,2);
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711153","c","201711153","12345678901",1,1,2);
#��ͨ�û�
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711154","d","201711154","12345678901",0,2,3);
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711155","e","201711155","12345678901",0,2,3);
insert into user(gh,name,pwd,phone,pow,manager_id,bm_id) 
values("201711156","f","201711156","12345678901",0,3,4);

#���ű�
create table bumen(
	id int auto_increment primary key,
	name varchar(20) 
);
insert into bumen(name) values("1");
insert into bumen(name) values("2");
insert into bumen(name) values("3");
insert into bumen(name) values("4");

#�㱨��
create table hb(
	id int auto_increment primary key,
	ty int,#�㱨���͡�0-�ܱ���1-�±���2-�걨
	rq varchar(20),#�㱨���������ڣ�XXXX��XX��XX��
	zyys int,#����/�¶�/���
	bmName varchar(20),#��������
	m_name varchar(20),#�쵼����
	csren varchar(20),#����������
	user_id int,#������id
	st int#�㱨��״̬��0-��ʾû���ʼ���1-�Ѿ����ͣ�2-�ȴ����ͣ�3-�Ѿ�����
);

#����/����/����ȱ���Ϊ�㱨���������ӱ����ڡ�
create table nowtask(
	id int auto_increment primary key,
	cdtask varchar(3000),#�е�������
	xdrq varchar(30),#�´����ڣ����ڸ�ʽ��XXX��XXX��XXX��
	wcrq varchar(30),#������ڣ����ڸ�ʽ��XXX��XXX��XXX��
	hb_id int#�㱨id
);

#����/����/����ȱ���Ϊ�㱨����������ڡ�
create table nexttask(
	id int auto_increment primary key,
	cdtask varchar(3000),#�е�������
	rq varchar(30),#Ԥ��������ڣ����ڸ�ʽ��XXX��XXX��XXX��
	zr varchar(30),#������
	xtbm varchar(30),#Э������
	bz varchar(3000),#��ע
	hb_id int#�㱨id
);

#�����������Ϊ�㱨����������ڡ�
create table wenti(
	id int auto_increment primary key,
	cdtask varchar(3000),#�е�������
	zywt varchar(3000),#��Ҫ����
	dcjjy varchar(3000),#�Բ߼�����
	hb_id int#�㱨��ID
);