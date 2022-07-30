# Blogging backend using SpringBoot + Hibernate + MySQL

Used :
- Layered architecture (Controller <--> Service <--> Repository)
- Mysql Workbench
- CRUD Functionality

## Screenshots of demo
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
  - Lombok (modified - included latest version)
