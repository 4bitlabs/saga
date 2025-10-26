# Etapa de build
FROM bellsoft/liberica-openjdk-alpine:21 AS build
WORKDIR /app

# Instalar bash para compatibilidade
RUN apk add --no-cache bash

# Copiar os arquivos de configuração Maven
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw && sed -i 's/\r$//' ./mvnw

# Baixar dependências do projeto
RUN bash ./mvnw dependency:go-offline

# Copiar o código-fonte e compilar
COPY src/ src/
RUN bash ./mvnw clean package -DskipTests

# Etapa de execução
FROM bellsoft/liberica-runtime-container:jre-21-musl
WORKDIR /app

# Copiar o JAR gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
