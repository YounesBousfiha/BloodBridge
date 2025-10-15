### Mutli-Stage Building

# STAGE 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# STAGE 3: Final stage to run the application
FROM tomcat:10.1.30-jdk21-temurin
COPY --from=build /app/target/BloodBridge.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]