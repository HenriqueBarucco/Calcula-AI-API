FROM curlimages/curl:8.2.1 AS download
WORKDIR /download
RUN  wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

FROM gradle:jdk21-alpine AS build

WORKDIR /build
ADD . .

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar -x test

FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache tzdata

WORKDIR /app

COPY --from=build /build/build/application.jar app.jar
COPY --from=download /download/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENV TZ=America/Sao_Paulo

EXPOSE 8080

ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar", "-jar", "app.jar"]