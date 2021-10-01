FROM gradle:7.2.0-jdk11 AS build
ENV APP_HOME=/home/app
COPY . $APP_HOME
RUN gradle :message-producer:build -x test -p $APP_HOME

FROM tomcat:9.0
ENV TOMCAT_VERSION 9.0.53
COPY --from=build /home/app/message-producer/build/libs/*producer.war /usr/local/tomcat/webapps/app.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
