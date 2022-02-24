pipeline {
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'c17be7b7-9f2c-46ba-b06b-2bc383d7d0aa', url: 'https://github.com/how2fps/y2s2devops']]])
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
