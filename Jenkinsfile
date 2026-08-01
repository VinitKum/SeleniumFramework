pipeline {

    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

    }

    post {

        always {
            junit 'test-output/junitreports/*.xml'
        }

    }

}