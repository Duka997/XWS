FROM maven:3.6.3-ibmjava-8-alpine AS zuul-back

WORKDIR /usr/src/zuul
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /zuul
COPY --from=zuul-back /usr/src/zuul/target/zuul-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "zuul-0.0.1-SNAPSHOT.jar"]

