pipeline {
  agent any

  triggers {
    pollSCM('* * * * *')
  }

  stages {

    stage('Compile') {
      steps {
        echo 'Compiling'
        gradlew('clean')
        }
      }

    stage('Build') {
      steps {
        echo 'Building'
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