pipeline {
  agent any
  environment{
    DOCKER_CREDENTIALS = credentials('docker-hub')
  }
  triggers {
    pollSCM('* * * * *')
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
  }
}