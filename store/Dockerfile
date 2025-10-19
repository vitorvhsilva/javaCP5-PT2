FROM eclipse-temurin:21-jdk-alpine

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
