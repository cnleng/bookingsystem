# Stage 1: Build the application
FROM gradle:jdk21 AS build

WORKDIR /app

# Copy Gradle files
COPY build.gradle settings.gradle /app/

# Copy application source code
COPY src /app/src

# Build the application
RUN gradle wrapper && ./gradlew clean build

FROM openjdk:21-jdk

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/build/libs/bookingsystem-0.0.1.jar /app/bookingsystem.jar

EXPOSE 5005
EXPOSE 8080
# Set the startup command for the Spring Boot application
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/bookingsystem.jar"]