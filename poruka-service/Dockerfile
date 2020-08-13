FROM maven:3.6.3-ibmjava-8-alpine AS poruka-service-back

WORKDIR /usr/src/poruka-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /poruka-service
COPY --from=poruka-service-back /usr/src/poruka-service/target/poruka-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "poruka-service-0.0.1-SNAPSHOT.jar"]

