FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/DBPractice-0.0.1-SNAPSHOT.jar /app/DBPractice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/DBPractice-0.0.1-SNAPSHOT.jar"]
