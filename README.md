# BloodBridge

Brief 3: App de Gestion de Banque de Sang

## Description

BloodBridge is a web application designed for managing blood bank operations. It facilitates the tracking of blood donors, receivers, and donations to ensure efficient and safe blood supply management. The application supports donor eligibility checks, receiver urgency prioritization, and donation history recording.

## Architecture

BloodBridge is built following **Domain-Driven Design (DDD)** and **Clean Architecture** principles, organizing the codebase into distinct layers:

- **Domain Layer**: Core business entities, enums, and business rules
- **Application Layer**: Services, commands (Command Pattern), mappers, and validations
- **Infrastructure Layer**: Data persistence, logging, and external integrations
- **Presentation Layer**: Front Controller (DispatcherServlet), DTOs, and web resources

The application uses the **Command Pattern** for request handling and a **Front Controller** pattern for centralized request routing, promoting separation of concerns and maintainability.

## Features

- **Donor Management**: Register donors with personal details, blood type, contraindications, eligibility status, weight, and last donation date.
- **Receiver Management**: Manage receivers including urgency levels, status, and count of received blood bags.
- **Donation Tracking**: Record blood donations linking specific donors to receivers with donation dates.
- **Blood Type Support**: Handles various blood types for compatibility checks.
- **Data Validation**: Ensures data integrity with Jakarta Validation constraints.
- **Web Interface**: Basic JSP-based UI for interacting with the system.

## Technologies Used

- **Language**: Java 21
- **Framework**: Jakarta EE (Servlet, Persistence, Validation)
- **ORM**: Hibernate 6.2
- **Database**: PostgreSQL 16
- **Web Server**: Apache Tomcat 10.1
- **Build Tool**: Apache Maven
- **Testing**: JUnit 5, Mockito
- **Logging**: SLF4J, Logback
- **Frontend**: JSP, JSTL, CSS, JavaScript
- **Containerization**: Docker, Docker Compose

## Prerequisites

- Java Development Kit (JDK) 21
- Apache Maven 3.9+
- Docker and Docker Compose (for containerized deployment)
- PostgreSQL (for local database setup)

## Installation and Setup

### Using Docker Compose (Recommended)

1. Clone the repository to your local machine.
2. Navigate to the project directory (`BloodBridge/`).
3. Run the following command to build and start the application:

   ```bash
   docker-compose up --build
   ```

4. The application will be accessible at `http://localhost:8080/BloodBridge`.
5. The database is available at `localhost:5432` with credentials: user `younes`, password `test123`, database `bloodbridge`.

### Manual Setup

1. Install and configure PostgreSQL:
   - Create a database named `bloodbridge`.
   - Set up a user with appropriate permissions.

2. Update the database connection settings in `src/main/resources/META-INF/persistence.xml` or relevant configuration files.

3. Build the project:
   ```bash
   mvn clean package -DskipTests
   ```

4. Deploy the generated `BloodBridge.war` file to Apache Tomcat:
   - Copy the WAR file to Tomcat's `webapps` directory.
   - Start Tomcat.

5. Access the application at `http://localhost:8080/BloodBridge`.

### Deployment Script

A deployment script `deploy.sh` is provided for local Tomcat deployment:
- Ensure Tomcat is installed at `/opt/tomcat`.
- Run `./deploy.sh` to build, deploy, and restart Tomcat.

## Usage

- **Web Interface**: Use the JSP pages to add, view, and manage donors, receivers, and donations.
- **API/Endpoints**: Interact with the application through web forms and servlets for CRUD operations.

## Project Structure

```
BloodBridge/
├── src/
│   ├── main/
│   │   ├── java/com/jartiste/bloodbridge/
│   │   │   ├── application/          # Application services, commands, mappers, validations
│   │   │   ├── domain/               # Domain entities, enums, exceptions
│   │   │   ├── infrastructure/       # Persistence, bootstrap logic
│   │   │   └── presentation/         # Web controllers and handlers
│   │   ├── resources/
│   │   │   ├── META-INF/persistence.xml
│   │   │   └── logback.xml
│   │   └── webapp/                   # Web resources (JSP, CSS, JS)
│   └── test/java/                    # Unit tests
├── Dockerfile                        # Docker build configuration
├── docker-compose.yml                # Multi-container setup
├── pom.xml                           # Maven configuration
└── deploy.sh                         # Deployment script
```

## Testing

- Run unit tests with Maven:
  ```bash
  mvn test
  ```
- Test coverage reports are generated in `target/site/jacoco/`.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push to the branch.
5. Open a Pull Request.

## License

This project is developed as part of an academic brief and does not specify a license.
