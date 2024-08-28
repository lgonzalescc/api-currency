FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/api-currency-0.0.1-SNAPSHOT.jar /app/api-currency.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/api-currency.jar"]