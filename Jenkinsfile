pipeline {
  agent any

  triggers {
    pollSCM('* * * * *')
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
      stage('Build Docker Image') {
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

//       stage ('Build & Push docker image') {
//         environment {
//             DOCKER_HUB_LOGIN = credentials('docker-hub')
//         }
//         steps {
//           withDockerRegistry(credentialsId: $'docker-hub', url: 'https://hub.docker.com/') {
//           sh 'docker push justacoder7/football-league-app'
//           }
//         }
//       }

//       stage('Push Docker image') {
//           environment {
//               DOCKER_HUB_LOGIN = credentials('docker-hub')
//           }
//           steps {
//               sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
//               sh './gradlew dockerPush'
//           }
//       }
  }
}