FROM openjdk:21 as build

LABEL authors="denmo"

WORKDIR /app

COPY . /app/.

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]