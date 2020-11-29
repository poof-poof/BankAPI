FROM maven:3.6.1-jdk-8-alpine as MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package -U

FROM openjdk:8-alpine

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/BankAPI-1.0-SNAPSHOT.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "-DPROFILE=dev" ,"app.jar"]