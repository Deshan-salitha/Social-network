# Social Network

This project is a Spring Boot REST API project configured to use an in-memory database for development and testing.

## *Prerequisites*
- Java ***17*** or later
- Apache Maven ***3.6.0***

## *Dependencies*

The project uses the following dependencies for in-memory database functionality:

- Spring Boot Starter Data JPA
- H2 Database

## *Build Instructions*

1. Open a terminal in the project directory.
1. Run the following command to build the project:
```Bash
mvn clean package
```
This will create a JAR file in the target directory containing the application.

## *Run Instructions*

To run the application with the main database configuration:
```Bash
mvn spring-boot:run
```
## *Test Instructions*

To run Unit tests using Maven:
```Bash
mvn test
```
### All the API endpoints and example request bodies and responses are in the,
- musala soft.postman_collection-final.json
- musala soft.postman_collection-final.yaml
