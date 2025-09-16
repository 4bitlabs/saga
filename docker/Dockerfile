FROM bellsoft/liberica-native-image-kit-container:jdk-21-nik-23-musl AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml .
RUN chmod +x ./mvnw
#Downloading project's dependencies
RUN ./mvnw dependency:go-offline
COPY src/ src/
# https://docs.spring.io/spring-boot/how-to/native-image/developing-your-first-application.html#howto.native-image.developing-your-first-application.native-build-tools.maven
RUN ./mvnw -Pnative native:compile -DskipTests

FROM alpine:3.22.1
WORKDIR /app
# `sighas` defined in <imageName>sighas</imageName> on pom.xml
COPY --from=build /app/target/sighas .
EXPOSE 8080
ENTRYPOINT ["./sighas"]