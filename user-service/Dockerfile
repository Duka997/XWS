FROM maven:3.6.3-ibmjava-8-alpine AS user-service-back

WORKDIR /usr/src/user-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /user-service
COPY --from=user-service-back /usr/src/user-service/target/user-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]

