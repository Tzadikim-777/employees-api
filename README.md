# Employees API

REST API employee, developed with Java 17,
Spring Boot 2.7.18 and Hexagonal Architecture.


## Technogolies

- Java 17
- Spring Boot 2.7.18
- Spring Data JPA 
- Oracle Database XE 21c
- MapStruct
- Lombok
- Maven
- JUnit 5 and Mockito
- Swagger
- Docker

## Database Configuration

Database: Oracle XE 21c

Service name: XEPDB1

Application schema: EMPLOYEES_DEV

Required objects:

- EMPLOYEES table
- EMPLOYEES_SEQ sequence

Configure the enviroment variable DB_PASSWORD

Database connection:

```
jdbc:oracle:thin:@//localhost:1521/XEPDB1
```

## Build

Package the application using Maven:

```
mvn clean package -DskipTests
```

## Run locally

Configure DB_PASSWORD and run:

```
mvn spring-boot:run
```

Default URL:

http://localhost:8080/employees