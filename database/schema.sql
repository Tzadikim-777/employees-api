CREATE TABLE employees ( id number(19) NOT NULL,
                         name varchar2(200) NOT NULL,
                         paternal_surname varchar2(100),
                         maternal_surname varchar2(100),
                         age number(10) NOT NULL,
                         sex varchar2(20),
                         birthdate date not null,
                         position varchar2(100) NOT NULL,
                         create_date TIMESTAMP(6) not null,
                         active NUMBER(1,0) not null,                         
                         CONSTRAINT employees_pk PRIMARY KEY (id) ); 
                         
                         
CREATE SEQUENCE employees_seq
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9999999999
NOCYCLE
NOCACHE;
                         