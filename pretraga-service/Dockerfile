FROM maven:3.6.3-ibmjava-8-alpine AS pretraga-service-back

WORKDIR /usr/src/pretraga-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /pretraga-service
COPY --from=pretraga-service-back /usr/src/pretraga-service/target/pretraga-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "pretraga-service-0.0.1-SNAPSHOT.jar"]

