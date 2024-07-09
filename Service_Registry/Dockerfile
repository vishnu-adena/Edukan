## Use the latest Ubuntu image as base
#FROM ubuntu:latest
#
## Install Java and Maven
#RUN apt-get update && apt-get install -y openjdk-21-jdk maven
#
## Set JAVA_HOME environment variable
#ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
#
## Add Java binaries to PATH
#ENV PATH $JAVA_HOME/bin:$PATH
#
## Set the working directory
#WORKDIR /app
#
## Copy the Maven project files to the container
#COPY . .
#
## Build the application
#RUN mvn clean package -DskipTests
#
## Expose the port the application runs on
#EXPOSE 8761
#
## Set the entry point to run the application
#ENTRYPOINT ["java", "-jar", "target/serviceRegistry-0.0.1.jar"]
# Use an official Java runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the local JAR file to the container
COPY target/serviceRegistry-0.0.1.jar app.jar

# Expose the port the application runs on
EXPOSE 8761

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
