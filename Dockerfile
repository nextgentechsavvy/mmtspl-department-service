FROM openjdk:8
EXPOSE 9001
ADD target/mmtspl-department-service-1.0.0-SNAPSHOT.jar mmtspl-department-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/mmtspl-department-service-1.0.0-SNAPSHOT.jar"]