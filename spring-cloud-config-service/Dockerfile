FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8888
COPY target/*.jar config-service.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=json-logs ${JAVA_OPTS} -jar /config-service.jar"]