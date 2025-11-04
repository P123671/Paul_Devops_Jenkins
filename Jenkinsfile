pipeline {
    agent any
    tools {
        maven 'Maven'
    }
 
    stages {
        stage('Checkout') {
            steps {
                git branch: 'DayTwoJunitTest', url: 'https://github.com/P123671/Paul_Devops_Jenkins.git'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }
 
        stage('Linter Check (Checkstyle)') {
            steps {
                // Run the Checkstyle plugin
                sh 'mvn checkstyle:checkstyle'
            }
            post {
                always {
                    recordIssues tools: [checkStyle(pattern: '**/target/checkstyle-result.xml')]
                }
                unstable {
                    echo '⚠️ Linter found warnings.'
                }
                failure {
                    echo '❌ Build failed due to style violations.'
                }
            }
        }
 
        stage('Formatter Check (Spotless)') {
            steps {
                // Verify formatting consistency (does NOT change code)
                sh 'mvn spotless:check'
            }
        }
 
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo '✅ Build, lint, and format checks passed!'
        }
        failure {
            echo '❌ Code style or format issues detected.'
        }
    }
}
