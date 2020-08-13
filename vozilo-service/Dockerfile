FROM maven:3.6.3-ibmjava-8-alpine AS vozilo-service-back

WORKDIR /usr/src/vozilo-service
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /vozilo-service
COPY --from=vozilo-service-back /usr/src/vozilo-service/target/vozilo-service-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "vozilo-service-0.0.1-SNAPSHOT.jar"]

