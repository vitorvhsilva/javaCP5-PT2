# Etapa 1: Construção do Projeto (Build)
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código-fonte e compile o projeto (gerando o .jar)
COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.compiler.parameters=true

# Etapa 2: Imagem de Execução (Runtime)
FROM openjdk:21-jdk-slim

# Variáveis de ambiente
ENV APP_USER=appuser \
    APP_HOME=/opt/alert-haven \
    APP_PORT=8080 \
    SPRING_PROFILES_ACTIVE=oracle \
    SPRING_DATASOURCE_PASSWORD=fiap25

# Criar um usuário não-root para rodar a aplicação
RUN useradd -m -d ${APP_HOME} ${APP_USER} && \
    chown ${APP_USER}:${APP_USER} ${APP_HOME}

# Definir o diretório de trabalho da aplicação
WORKDIR ${APP_HOME}
RUN chown ${APP_USER}:${APP_USER} ${APP_HOME}

# Copiar o JAR gerado para o container
COPY --from=build --chown=${APP_USER}:${APP_USER} /app/target/store-0.0.1-SNAPSHOT.jar ${APP_HOME}/app.jar

# Mudar para o usuário não-root
USER ${APP_USER}

# Expor a porta para o serviço
EXPOSE ${APP_PORT}

# Iniciar a aplicação com as variáveis de ambiente passadas dinamicamente
CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}", "-jar", "app.jar"]
