# Use a lightweight JDK image
FROM eclipse-temurin:20-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from your build context to the container
COPY target/news-aggregator-0.0.1.jar news-aggregator-0.0.1.jar

# Set the active Spring profile to "docker"
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-default}

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "news-aggregator-0.0.1.jar"]
