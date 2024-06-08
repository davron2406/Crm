FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
VOLUME /tmp
COPY target/CRM-0.0.1-SNAPSHOT.jar crm.jar
ENTRYPOINT["java","-jar","crm.jar"]
EXPOSE 8080