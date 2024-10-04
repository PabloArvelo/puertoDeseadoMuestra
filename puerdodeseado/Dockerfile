FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/puerto_deseado-0.0.1.jar
COPY ${JAR_FILE} puerto_deseado.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "puerto_deseado.jar"]