# StreamCDA - VOD Platform Backend
## About The Project
StreamCDA is a backend application for a VOD streaming platform built with Java and Spring Boot.
The project focuses on creating a secure and scalable REST API for managing movies, users, and premium subscriptions.

The application was developed as a portfolio and learning project to improve backend development skills and learn modern authentication systems using JWT.

### Built With
The project uses several libraries and frameworks that allow to speed up the work on the target functionality, i.e. providing a secure, role-based, and well-developed API for the VOD system. The main dependencies used are:

**Spring Boot**

**Spring Security (JWT Authentication)**

**Spring Data JPA / Hibernate**

**Lombok**

**H2 Database Engine / MySQL**

**OpenAPI 3 / Swagger UI**

**JUnit 5**

**Mockito**

**MySQL**

**Maven**

## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

To be able to run the project, Java 21+ (or OpenJDK 26) and the Maven build tool are required to download all dependencies. It is also recommended to use IntelliJ IDEA, but it depends on your preferences.

### Installation

Clone the repo: ```git clone https://github.com/ChoesMad/streamcda-backend```

Open the project using your IDE.

Reload the project using Maven to download the dependencies: ```mvn clean install -U -DskipTests```

## Usage

Once the project has been successfully launched, you can access its endpoints locally. The default address for this is: 
```
http://localhost:8080/
```

To use the Swagger UI tool to test the available endpoints, go to the address below:
```
http://localhost:8080/swagger-ui/index.html
```

To be able to use protected endpoints and requests in Swagger (such as adding movies or purchasing subscriptions), you need to obtain a Bearer token:

Go to POST /api/auth/login and provide credentials.

Copy the generated JWT token from the response.

Click the green Authorize button at the top of the Swagger page.

Paste the token into the value field as: Bearer <your_copied_token>.

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.
