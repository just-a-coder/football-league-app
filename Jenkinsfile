pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
    
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage ('Build docker image') {
            steps {
                sh 'docker build -t justacoder7/football-league-app .'
            }
        }
    }
}