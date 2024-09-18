FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY ./target/forum-service-0.0.1-SNAPSHOT.jar ./forum-service.jar

ENV MONGODB_URI=mongodb+srv://vvsst1:12345.com@java53cluster.pgecd.mongodb.net/java53db?retryWrites=true&w=majority&appName=java53cluster

CMD ["java", "-jar", "/app/forum-service.jar"]
