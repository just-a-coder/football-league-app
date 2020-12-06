pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    
    stages {

        stage ('Test and Build') {
            agent {
                docker {
                    image 'openjdk:11'
                    args '-v "$PWD":/app'
                    reuseNode true
                }
            }
            steps {
                sh './gradlew clean build'
            }
        }

        stage ('Build docker image') {
            steps {
                sh 'docker build -t justacoder7/football-league-app .'
            }
        }
    }
}