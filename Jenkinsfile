pipeline {

    agent any

    tools {
        maven 'Maven3'
    }

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

            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            )

        }

    }

}