FROM openjdk:8-jdk-alpine

MAINTAINER techbow

RUN addgroup -S spring && adduser -S spring -G spring
RUN chown spring:spring "/var/log"
USER spring:spring

RUN mkdir -p "/var/log/spring-boot"

HEALTHCHECK CMD curl --fail –s  http://localhost:8083/actuator/health/liveness || exit 1 

WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} "./app.jar"
ENTRYPOINT ["java", "-jar", "./app.jar"]
