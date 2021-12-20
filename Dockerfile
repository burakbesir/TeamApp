FROM maven:3.8-openjdk-17-slim as BUILD
COPY . .
RUN mvn -DskipTests clean package

FROM openjdk:17.0.1-jdk-slim
COPY --from=BUILD /target/team-app.jar .
EXPOSE 8080
CMD ["java","-jar","team-app.jar"]