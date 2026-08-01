# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /build
COPY app/.mvn/ .mvn/
COPY app/mvnw ./
COPY app/pom.xml ./
# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline
COPY app/src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Run as non-root user for security (Kubernetes best practice)
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

COPY --from=builder /build/target/*.jar app.jar

# Expose standard port and actuator port
EXPOSE 8080

# Run with JVM memory optimizations for containers
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
