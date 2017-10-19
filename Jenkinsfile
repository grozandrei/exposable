pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git(url: 'https://github.com/grozandrei/exposable', branch: 'master')
      }
    }
  }
}