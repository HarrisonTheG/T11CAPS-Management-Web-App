# T11CAPS
Management System created with Spring Boot and MySQL.

## General information
Course Application Processing System(CAPS) is a management information system for academic institutions to manage and process course enrolment.
	
## Technologies
Project is created with:
* JDK version: 1.8
* Spring Boot version: 2.5.1
* MySQL version: 8.0

	
## Setup
To import and run this project, you will need Spring Tool Suite 4 and any tools that use MySQL such as HeidiSQL
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

## Logging In
In the Seed Database, we created dummy names and details. As these is management system involving many identities, for testing purpose:
1. Login as Students using: e0696695@u.nus.edu (Email) and 123 (password)
2. Login as Lecturer using: Tin@nus.edu.sg (Email) and 123 (password)
3. Login as Admin using: megan@nus.edu.sg (Email) and 123 (password)
* they are case-sensitive

## Email Notification
Email Notification works only for STUDENT and LECTURER in the scenario of a successful course enrollment or dismissal of student/lecturer enrollment by admin.
For email notification to work, please modify the student's or lecturer's email to legitimate real email address or enrollment and dismissal (by admin) will result in an error.
