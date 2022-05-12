# Java 11 base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Build path
ARG JAR_FILE=build/libs/api-1.0.0.jar

# cd /opt/app
WORKDIR /opt/app

# cp build/libs/api-1.0.0.jar /opt/app/stock-api.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/stock-api.jar
ENTRYPOINT ["java","-jar","app.jar"]