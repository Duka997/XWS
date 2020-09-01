FROM maven:3.6.3-ibmjava-8-alpine AS demo-back

WORKDIR /usr/src/demo
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /demo
COPY --from=demo-back /usr/src/demo/target/demo-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

