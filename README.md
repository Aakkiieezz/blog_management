# Blogging backend using SpringBoot + Hibernate + MySQL

Used :
- Layered architecture (Controller <--> Service <--> Repository)
- Mysql Workbench
- CRUD Functionality

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
  - Lombok (modified - included latest version)
