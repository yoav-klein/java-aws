FROM maven:3.8.5-openjdk-18 AS builder

COPY . /code

WORKDIR /code

RUN mvn package

# Use a base image with Java (replace 'openjdk:11' with your desired Java version)
FROM openjdk:8

# Set the working directory inside the Docker image
WORKDIR /app

# Copy the JAR file from the target directory into the image
COPY --from=builder /code/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar /app/my-app.jar


# Command to run your application when the container starts
CMD ["java", "-jar", "my-app.jar"]

