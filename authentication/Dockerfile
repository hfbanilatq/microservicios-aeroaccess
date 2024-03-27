FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD
LABEL maintainer hfbanilatq
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package -DskipTests
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/authentication-0.0.1.jar /app/
ENTRYPOINT ["java", "-jar", "authentication-0.0.1.jar"]
