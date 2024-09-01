FROM amazoncorretto:17-alpine-jdk

COPY out\artifacts\properties_backend_jar\properties-backend.jar /app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-jar", "/app.jar"]