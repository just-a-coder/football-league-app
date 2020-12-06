
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/football-league-app.jar

WORKDIR /opt/app

COPY ${JAR_FILE} football-league-app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","football-league-app.jar"]

# FROM jenkins/jenkins:2.225
#
# USER root
# RUN curl -sSL https://get.docker.com/ | sh
# RUN usermod -a -G docker jenkins
# USER jenkins
#
# COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
# RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
#
# COPY seedJob.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml
#
# ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false