FROM gradle:jdk21-alpine AS build

WORKDIR /build
ADD . .

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache tzdata

WORKDIR /app

COPY --from=build /build/build/application.jar app.jar

ENV TZ=America/Sao_Paulo

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
