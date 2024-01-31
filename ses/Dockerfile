FROM gradle:8.5.0-jdk17-focal AS builder

COPY . /code

WORKDIR /code

RUN gradle build

# Use a base image with Java (replace 'openjdk:11' with your desired Java version)
FROM openjdk:17

# Set the working directory inside the Docker image
WORKDIR /app

# Copy the JAR file from the target directory into the image
COPY --from=builder /code/app/build/libs/app.jar /app/app.jar


# Command to run your application when the container starts
CMD ["java", "-jar", "app.jar"]

