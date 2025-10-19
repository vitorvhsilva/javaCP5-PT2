FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.compiler.parameters=true

FROM openjdk:21-jdk-slim

ENV APP_USER=appuser \
    APP_HOME=/opt/cp5-pt2 \
    APP_PORT=8081 \
    SPRING_PROFILES_ACTIVE=oracle \
    SPRING_DATASOURCE_PASSWORD=fiap25

RUN useradd -m -d ${APP_HOME} ${APP_USER} && \
    chown ${APP_USER}:${APP_USER} ${APP_HOME}

WORKDIR ${APP_HOME}
RUN chown ${APP_USER}:${APP_USER} ${APP_HOME}

COPY --from=build --chown=${APP_USER}:${APP_USER} /app/target/store-0.0.1-SNAPSHOT.jar ${APP_HOME}/app.jar

USER ${APP_USER}

EXPOSE ${APP_PORT}

CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", "-jar", "app.jar"]
