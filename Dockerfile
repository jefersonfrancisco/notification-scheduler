FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notification-scheduler.jar
ENTRYPOINT ["java","-jar","/notification-scheduler.jar"]