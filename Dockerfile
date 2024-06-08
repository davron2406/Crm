FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
VOLUME /tmp
COPY target/*jar app.jar
ENTRYPOINT["java","-jar","/app.jar"]
EXPOSE 8080