# Stage 1: Build the Java application with Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /masai
COPY . ./
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight Java runtime image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /masai
COPY --from=build /app/target/CA01_Institute_Management-0.0.1-SNAPSHOT.jar CA01_Institute_Management.jar
EXPOSE 8080
CMD ["java", "-jar", "CA01_Institute_Management.jar"]