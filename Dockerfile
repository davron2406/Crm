FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/CRM-0.0.1-SNAPSHOT.jar CRM.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","CRM.jar"]