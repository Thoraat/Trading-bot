# # Use an official OpenJDK runtime as a parent image
# FROM openjdk:17-jdk-slim
#
# # Set the working directory inside the container
# WORKDIR /app
#
# # Copy the JAR file built by Maven
# COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
#
# # Expose the port your Spring Boot app runs on
# EXPOSE 8080
#
# # Run the JAR file
# ENTRYPOINT ["java","-jar","app.jar"]

# Use a compatible Java version
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Build the application (for Maven)
RUN apk add --no-cache maven
RUN mvn package -DskipTests

# Alternatively for Gradle, use this instead:
# COPY build.gradle .
# COPY gradlew .
# COPY gradle ./gradle
# RUN ./gradlew build -x test

# Expose the port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/your-app-name-0.0.1-SNAPSHOT.jar"]