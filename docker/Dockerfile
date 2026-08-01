# Stage 1: Build the application using standard Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

# Copy source code and POM together, then build
COPY app/pom.xml .
COPY app/src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Non-root security user
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
