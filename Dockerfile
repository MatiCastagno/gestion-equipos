FROM openjdk:17-alpine

WORKDIR /app

COPY target/gestion-equipos-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
