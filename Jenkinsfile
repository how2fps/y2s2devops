pipeline {
  agent any
  stages {
    stage('Checkout Scm') {
      steps {
        git 'https://github.com/how2fps/y2s2devops'
      }
    }

    stage('Maven Build 0') {
      steps {
        bat 'mvn clean install test'
      }
    }

  }
  tools {
    maven 'maven 3.8.2'
    jdk 'JDK 1.8'
  }
  post {
    always {
      echo 'No converter for Publisher: hudson.plugins.deploy.DeployPublisher'
    }

  }
  triggers {
    pollSCM('H * * * *')
  }
}
