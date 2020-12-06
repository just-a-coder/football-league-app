
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/football-league-app.jar

WORKDIR /opt/app

COPY ${JAR_FILE} football-league-app.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","football-league-app.jar"]