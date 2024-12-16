FROM openjdk:22-jdk
VOLUME /tmp
EXPOSE 8083
COPY target/gameservice-1.0.0.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar