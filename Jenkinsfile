pipeline {
  agent any

  triggers {
    pollSCM('* * * * *')
  }

  stages {

    stage('Build') {
      steps {
        echo 'Building'
        sh './gradlew clean build'
      }
    }

    stage('Unit & Integration Tests') {
      steps {
        script {
          try {
             sh './gradlew clean test --no-daemon' //run a gradle task
             } finally {
             junit '**/build/test-results/test/*.xml' //make the junit test results available in any case (success & failure)
          }
        }
      }
    }

    stage('Build Docker image') {
        steps {
            sh './gradlew docker'
        }
    }
    
    stage('Push Docker image') {
        environment {
            DOCKER_HUB_LOGIN = credentials('docker-hub')
        }
        steps {
            sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
            sh './gradlew dockerPush'
        }
    }

    stage('Deploy') {
      steps {
        echo 'Deploy'
      }
    }

  }
}