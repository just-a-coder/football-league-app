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

    stage('Deploy') {
      steps {
        echo 'Deploy'
      }
    }

  }
}