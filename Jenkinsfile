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

    stage('Test') {
      steps {
        echo 'Test'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploy'
      }
    }

  }
}