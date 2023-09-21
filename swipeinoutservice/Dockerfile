FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 9000
COPY target/*.jar swipe-service.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=json-logs ${JAVA_OPTS} -jar /swipe-service.jar"]