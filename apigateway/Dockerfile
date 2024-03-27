FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD
LABEL maintainer hfbanilatq
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package -DskipTests
FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/microservice-aeroaccess-api-gateway-0.0.1.jar /app/
CMD ["java", "-jar", "microservice-aeroaccess-api-gateway-0.0.1.jar"]