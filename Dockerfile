FROM openjdk:17-alpine
WORKDIR /app

EXPOSE 7070
EXPOSE 3306

COPY backend/src/target/lib/* target/lib/
COPY backend/src/target/backend-0.0.1-SNAPSHOT.jar target/app.jar

WORKDIR /app/target
ENTRYPOINT java -jar target/app.jar
