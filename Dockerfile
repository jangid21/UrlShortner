# Start with a base image that has Java
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project JAR file into the container
COPY target/url-shortener.jar /app/url-shortener.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "url-shortener.jar"]
