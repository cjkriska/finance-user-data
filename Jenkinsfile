pipeline {
    agent any
    tools {
        maven "jenkins-maven"
    }

    stages {
        stage('Build Artifact') {
            steps {
                sh "mvn clean package -DskipTests=true"
                archive 'target/*.jar'
            }
        }

        stage('Test Maven - JUnit') {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Snyk') {
            steps {
                snykSecurity(
                    snykInstallation: 'Snyk-Jenkins',
                    snykTokenId: 'snyk-jenkins'
                )
            }
        }
    }
}