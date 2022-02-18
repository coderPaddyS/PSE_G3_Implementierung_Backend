FROM openjdk:17-alpine
WORKDIR /app

EXPOSE 7070
EXPOSE 3306

COPY backend/target/lib/* target/lib/
COPY backend/target/backend-0.0.1-SNAPSHOT.jar target/app.jar

WORKDIR /app/target
ENTRYPOINT java -jar app.jar
