# Use an official OpenJDK runtime image with Java 17
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/demo-pdf-document-0.0.1-SNAPSHOT.jar /app/demo-pdf-document.jar

# Copy the inputfile directory into the container
COPY inputfile /app/inputfile

# Copy the fonts into the container
COPY src/main/resources/fonts /usr/share/fonts/truetype/dejavu


# Expose port 8080 to the outside world
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "demo-pdf-document.jar"]
