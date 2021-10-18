FROM tomcat:9.0
ENV TOMCAT_VERSION 9.0.53
ADD ./message-producer/build/libs/*producer.war /usr/local/tomcat/webapps/app.war
EXPOSE 8080
ENV JAVA_OPTS "-Dspring.rpofiles.active=dev"
CMD ["catalina.sh", "run"]
