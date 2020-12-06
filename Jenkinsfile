pipeline {
  agent any
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
        sh 'jenkins/test-all.sh'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Deploy'
        sh 'jenkins/deploy.sh'
      }
    }

  }
}