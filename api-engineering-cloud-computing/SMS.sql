DROP DATABASE SMS;

CREATE DATABASE SMS;

USE SMS;

CREATE TABLE SMS.Course(
	CourseCode varchar(10)  PRIMARY KEY NOT NULL,
	CourseTitle varchar(50) NOT NULL,
	TotalCourseHours int NOT NULL,
	School varchar(50) NOT NULL,
	Department varchar(50) NOT NULL);

insert into Course values('COMP212','Programming III', 56,'SETAS','ICET');
insert into Course values('COMP110','Programming I', 56,'SETAS','ICET');
insert into Course values('COMP214','Advanced Database Concepts', 56,'SETAS','ICET');
insert into Course values('COMP228','Java Programming', 56,'SETAS','ICET');
insert into Course values('COMP306','Web Services Programming', 56,'SETAS','ICET');
insert into Course values('COMP311','Software Testing and Quality Assurance', 56,'SETAS','ICET');
insert into Course values('COMP309','Data Warehouse & Mining - HCIS', 56,'SETAS','ICET');


CREATE TABLE SMS.Student(
	StudentID varchar(10) PRIMARY KEY NOT NULL,
	FirstName varchar(50) NULL,
	LastName varchar(50) NULL,
	Program varchar(8) NULL);
	
insert into Student values('300111222','Cindy','Jones','3409');
insert into Student values('300222333','John','Smith','3419');
insert into Student values('300333444','Howard','Browns','3439');
insert into Student values('300444555','Trevor','Lee','3439');


CREATE TABLE SMS.Login(
	LoginName varchar(10)  PRIMARY KEY NOT NULL,
	Password varchar(12) NOT NULL,
	CONSTRAINT FK_Login_Student FOREIGN KEY (LoginName) REFERENCES SMS.Student(StudentID));

insert into Login values('300111222','password');
insert into Login values('300222333','test');
insert into Login values('300333444','password');
insert into Login values('300444555','password');


CREATE TABLE SMS.Enrollment(
	StudentID varchar(10) NOT NULL,
	CourseCode varchar(10) NOT NULL,
    CONSTRAINT PK_Enrollment PRIMARY KEY (StudentID,CourseCode),
	CONSTRAINT FK_Enrollment_Course FOREIGN KEY (CourseCode) REFERENCES SMS.Course(CourseCode),
	CONSTRAINT FK_Enrollment_Student FOREIGN KEY (StudentID) REFERENCES SMS.Student(StudentID));

insert into Enrollment values('300111222','COMP212');
insert into Enrollment values('300111222','COMP311');
insert into Enrollment values('300111222','COMP306');

insert into Enrollment values('300222333','COMP228');
insert into Enrollment values('300222333','COMP309');
insert into Enrollment values('300222333','COMP306');

insert into Enrollment values('300333444','COMP110');
insert into Enrollment values('300333444','COMP214');
insert into Enrollment values('300333444','COMP309');

insert into Enrollment values('300444555','COMP110');
insert into Enrollment values('300444555','COMP228');
insert into Enrollment values('300444555','COMP311');
