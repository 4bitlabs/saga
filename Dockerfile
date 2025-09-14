FROM --platform=$BUILDPLATFORM maven:3.9.6-eclipse-temurin-21-jammy AS build

COPY . .
RUN mvn clean install -DskipTests

FROM --platform=$TARGETPLATFORM eclipse-temurin:21-jre-jammy

WORKDIR /app

EXPOSE 8080

COPY --from=build /target/sighas-be-1.0.0v.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]