FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /hrisemp
COPY ./ /hrisemp
RUN mvn clean install -DskipTests
# Run spring boot in Docker

FROM openjdk:17
COPY --from=build /hrisemp/target/*.jar application.jar
ENV PORT 8090
EXPOSE $PORT
ENTRYPOINT ["java", "-jar","-Dserver.port=${PORT}", "application.jar"]
