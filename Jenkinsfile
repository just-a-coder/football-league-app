pipeline {
    agent { dockerfile true }

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage ('Build docker image') {
            steps {
                sh './gradlew clean build'
                sh 'docker build -t justacoder7/football-league-app .'
            }
        }
    }
}