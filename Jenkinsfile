pipeline {

    agent any

    stages {

        stage('Build & Test') {
            steps {
                tools {
   						maven 'Maven3'
					}
            }
        }

    }

    post {

        always {
            junit 'test-output/junitreports/*.xml'
        }

    }

}