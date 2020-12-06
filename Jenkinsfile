pipeline {
  agent any

  triggers {
    pollSCM('* * * * *')
  }

  stages {

    stage('Compile') {
      steps {
        echo 'Compiling'
        gradlew('clean', 'classes')
        }
      }

    stage('Build') {
      steps {
        echo 'Building'
        withGradle() {
          withGradle()
        }

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