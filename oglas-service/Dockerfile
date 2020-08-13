FROM maven:3.6.3-ibmjava-8-alpine AS oglas-service-back

WORKDIR /usr/src/oglas-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /oglas-service
COPY --from=oglas-service-back /usr/src/oglas-service/target/oglas-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "oglas-service-0.0.1-SNAPSHOT.jar"]

