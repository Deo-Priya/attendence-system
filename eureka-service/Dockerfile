FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8000
COPY target/*.jar eureka-service.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=json-logs ${JAVA_OPTS} -jar /eureka-service.jar"]