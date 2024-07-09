# Use a base image with Eclipse Temurin (AdoptOpenJDK) for Java 21
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/edukanUserService-1.0.0.jar /app/edukanUserService-1.0.0.jar

# Expose the port the application runs on
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/edukanUserService-1.0.0.jar"]
