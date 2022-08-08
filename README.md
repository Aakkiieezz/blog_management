# Blogging backend using SpringBoot + Hibernate + MySQL

Things implemented/used :
- 3 Layer architecture (Controller <--> Service <--> Repository[DAO])
- Api's for CRUD Operations (Create, Read, Update, Delete)
- Api's for Login & Register
- Api's for Pagination & Sorting
- Role based authentication (diff access for admins & users)
- Using DTO's (for data transfer)
- Validation Handling
- Exception Handling (for proper error message to user)
- Pagination
- JWT based authentication

Technologies/Tools used :
- Spring Boot Java Framework
- Maven Build Tool
- Visual Studio Code IDE
- Apache Tomcat (embedded web server in Spring)
- Spring Core
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- Mysql Workbench
- Postman Rest Client
- Swagger

## Screenshots of demo

ER Diagram
![Screenshot_20220729_151348](https://user-images.githubusercontent.com/67866166/183232229-c80b5a2c-f129-4fe2-8fd1-818f236d9675.png)

All Api's

![ref - all api's](https://user-images.githubusercontent.com/67866166/183232231-c62b4784-e124-4c44-88fc-701872513672.png)

Database - in MySQL Workbench

## VSCode
Extensions installed :
- Spring Boot Extension Pack
- Lombok Annotations Support for VS Code

Steps through command pallete (Ctrl+Shift+p) :
- Spring Initializr: Create a Maven Project
- Specific Spring Boot version - 2.7.2
- Specific project  language - Java
- Input Group Id - blog
- Artifact ID - blog_management
- Specific packaging type - Jar
- Specific Java version - 18
- Search for dependencies (reflects in pom.xml file):
  - Spring Boot DevTools
  - Spring Data JPA
  - MySQL Driver
  - Spring Web
  - Validation
  - Lombok (modified - included version 1.18.24)
  - ModelMapper (added externally version 3.1.0)
