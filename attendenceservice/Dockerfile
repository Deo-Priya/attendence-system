FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 9001
COPY target/*.jar attendence-service.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=json-logs ${JAVA_OPTS} -jar /attendence-service.jar"]