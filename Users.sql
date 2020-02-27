use d1876473;
create table USERS (ID int primary key auto_increment, USERNAME varchar(20) not null, 
FNAME varchar(100) not null, LNAME varchar(50) not null, 
PASSWORD longtext not null, STUDENT_NO varchar(20) not null, 
ICAM_No varchar(50) not null, CONTACT_NO char(10) not null,EMAIL_ADDRESS varchar(100) not null);