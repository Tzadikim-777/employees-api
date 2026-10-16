FROM eclipse-temurin:17-jre
WORKDIR /app


COPY target/com.invex.evaluation-0.0.1-SNAPSHOT.jar app.jar


COPY src ./src
EXPOSE 8080



USER 10001

ENTRYPOINT [ "java", "-jar","app.jar" ] 