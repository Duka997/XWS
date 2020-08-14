FROM maven:3.6.3-ibmjava-8-alpine AS zahtjev-service-back

WORKDIR /usr/src/oglas-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /zahtjev-service
COPY --from=oglas-service-back /usr/src/zahtjev-service/target/zahtjev-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "zahtjev-service-0.0.1-SNAPSHOT.jar"]

