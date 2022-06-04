FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN ["mkdir", "-p", "./src/main/resources/static/images"]
ENTRYPOINT ["java","-jar","/app.jar"]