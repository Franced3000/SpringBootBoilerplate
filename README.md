# Spring Boot Boilerplate
## Overview
This project is a boilerplate for a robust and versatile backend using Spring Boot 3. It includes features like user authentication and authorization with JWT, integration with MySQL database, and Spring Security configuration.

### Features
Spring Boot 3: Core framework for creating standalone applications.
MySQL: Database integration for data persistence.
Spring Security: Secure the application with JWT-based authentication.
JWT: JSON Web Token for stateless authentication.
Prerequisites
Java 17 or later
Maven
MySQL
Setup Instructions
Clone the Repository:

```
git clone https://github.com/Franced3000/SpringBootBoilerplate.git
cd SpringBootBoilerplate
```
### Configure MySQL:

Create a MySQL database named springbootboilerplate.
Update the database configuration in src/main/resources/application.properties:

properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/springbootboilerplate
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
### Build the Project:

```
mvn clean install
```
### Run the Application:

```
mvn spring-boot:run
```
### API Endpoints
#### Register a New User
URL: ```/api/register```\
Method: POST\
Body (JSON):
json
```
{
    "username": "testuser",
    "password": "testpassword",
    "role": "ROLE_USER"
}
```
#### Authenticate a User
URL: ```/api/authenticate```\
Method: POST\
Body (JSON):
json
```
{
    "username": "testuser",
    "password": "testpassword"
}
```
Response (JSON):
json
```
{
    "token": "xxxxxxxxx"
}
```
#### Access a Protected Endpoint
URL: ```/api/protected```\
Method: GET\
Headers:
Key: Authorization
Value: Bearer <JWT_TOKEN>
### Project Structure
src/main/java/com/example/SpringBootBoilerplate
controller: Contains REST controllers.
JwtAuthenticationController.java
model: Contains entity classes.
User.java
repository: Contains repository interfaces.
UserRepository.java
security: Contains security-related classes.
JwtAuthenticationEntryPoint.java
JwtRequestFilter.java
JwtTokenUtil.java
service: Contains service classes.
JwtUserDetailsService.java
config: Contains configuration classes.
SecurityConfig.java
### Key Classes
JwtAuthenticationController: Manages user authentication and registration.
JwtRequestFilter: Filters incoming requests to validate JWT tokens.
JwtTokenUtil: Utility class for handling JWT operations.
SecurityConfig: Configures Spring Security to use JWT for securing endpoints.
JwtUserDetailsService: Implements user details retrieval and user registration logic.
### Dependencies
Spring Boot Starter Web
Spring Boot Starter Security
Spring Boot Starter Data JPA
Spring Boot Starter Validation
Spring Boot Starter Test
MySQL Connector
Spring Security JWT
## Authors
Francesco Spata
## License
This project is licensed under the MIT License.

