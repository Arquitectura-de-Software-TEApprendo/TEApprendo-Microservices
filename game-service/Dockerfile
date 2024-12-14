FROM openjdk:22-jdk
VOLUME /tmp
EXPOSE 8080
COPY target/game-service-1.0.0.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar