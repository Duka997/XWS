FROM maven:3.6.3-ibmjava-8-alpine AS eureka-back

WORKDIR /usr/src/eureka
COPY . .
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /eureka
COPY --from=eureka-back /usr/src/eureka/target/eureka-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]

