FROM openjdk:11-jre-slim
ADD ./message-consumer/build/libs/*consumer.jar /app.jar
WORKDIR /
CMD ["java", "-jar", "app.jar"]