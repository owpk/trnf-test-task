FROM gradle:7.2.0-jdk11 AS build
ENV APP_HOME=/home/app
COPY . $APP_HOME
RUN gradle :message-consumer:build -x test -p $APP_HOME

FROM openjdk:11-jre-slim
COPY --from=build /home/app/message-consumer/build/libs/*consumer.jar /app.jar
WORKDIR /
CMD ["java", "-jar", "app.jar"]