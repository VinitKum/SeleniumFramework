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

            

            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            )

            emailext(
                subject: "Jenkins Build #${BUILD_NUMBER} - ${currentBuild.currentResult}",
                body: """Hello,

Jenkins build has completed.

Job Name: ${JOB_NAME}
Build Number: ${BUILD_NUMBER}
Build Status: ${currentBuild.currentResult}

Please check Jenkins for build details and Allure report.

Build URL:
${BUILD_URL}

Regards,
Jenkins
""",
                to: "vinit.upadhyay1987@gmail.com"
            )
        }

    }

}