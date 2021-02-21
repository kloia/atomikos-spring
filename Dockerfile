FROM openjdk:8-jdk-alpine

LABEL maintainer="orhanburak@kloia.com"

ARG JAR_FILE=target/atomikos-spring-1.0.0-SNAPSHOT.jar

ADD ${JAR_FILE} Application.jar

ENTRYPOINT ["java","-jar","Application.jar"]