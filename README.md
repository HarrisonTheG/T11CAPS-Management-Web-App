# T11CAPS
Management System created with Spring Boot and MySQL.

## Basic App Feature for CAPS:
1.	Three identities with different use cases:
	a.	Student: view own profile, view enrolled courses, view other courses, view course details, view course lecturer profile, view grades, enroll course, search courses, receive successful and unsuccessful email notification for course enrolment, login and logout.
	b.	Lecturer: view own profile, view student profile, view student performance, view enrolled courses, view course details, search student, search courses, grade student, receive successful and unsuccessful email notification for course enrolment, login and logout.
	c.	Admin: CRUD on student, lecturer profile, CRUD on course, CRUD on course-student and course-lecturer enrolment, view and update own profile, login and logout.
2.	Validation on login, admin registering for new student or lecturer.
3.	Exception Handling: custom error page when something went wrong.
4.	Email notification for student and lecturer when student successfully enrolled to a course, and when admin add or remove student/lecturer to/from a course.
5.	Javascript and CSS functionalities: on search bar and scrollable table with highlights to increase User Experience.
6.	Test Cases: for unit testing: to ensure proper function of CRUD persistency to database.
7.	Multiple layered architecture design for abstraction of an the working app.


## General information
Course Application Processing System(CAPS) is a management information system for academic institutions to manage and process course enrolment.
	
## Technologies
Project is created with:
* JDK version: 1.8
* Spring Boot version: 2.5.1
* MySQL version: 8.0

	
## Setup
To import and run this project, you will need Spring Tool Suite 4 and any tools that use MySQL such as HeidiSQL and ensure local MySQL connection is active.
* Import project from Git in STS
* Clone the URI from this repository https://github.com/HarrisonTheG/T11CAPS.git
* Create <b> application.properties </b> file under Resources folder and include these properties. Include your own username and password for the connection to MySQL database

```
spring.datasource.url=jdbc:mysql://localhost:3306/t11caps?useSSL=false&serverTimezone=Asia/Singapore
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
server.port= 8082
spring.jpa.hibernate.ddl-auto = update
spring.mail.host=smtp.163.com
spring.mail.username=PCXGudrew@163.com
spring.mail.password=VGNFCVQYNNMELYSA
spring.mail.default-encoding=UTF-8
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.port=465
spring.mail.properties.mail.smtp.socketFactory.port = 465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false   
```
* Create database query in MySQL using this code
```
CREATE SCHEMA t11caps;
USE t11caps;
```
* In T11CapsApplication file, uncomment these codes only once to seed the database to run application

```
//DbSeeding.SeedDBWithUsersAndCourses();
//DbSeeding.SeedDBforCoursesTeachedandEnrolled();
					
```
* For subsequent runs, re-comment the codes to disable futher seeding of database
* Use Chrome Browser to run application to ensure correct stylings.

## Logging In
In the Seed Database, we created dummy names and details. As these is management system involving many identities, for testing purpose:
1. Login as Students using: e0696695@u.nus.edu (Email) and 123 (password)
2. Login as Lecturer using: Tin@nus.edu.sg (Email) and 123 (password)
3. Login as Admin using: megan@nus.edu.sg (Email) and 123 (password)
* Note: <i> the dummy e-mails and passwords are case-sensitive. Emails are not real so change to make email notification works especially when admin is removing and adding student/lecturer to a course or student enrolling for a course. </i>

## Email Notification
Email Notification works only for STUDENT and LECTURER in the scenario of a successful course enrollment or dismissal of student/lecturer enrollment by admin.
For email notification to work, please modify the student's or lecturer's email to legitimate real email address or enrollment and dismissal (by admin) will result in an error. However, DB actions will still go through.
