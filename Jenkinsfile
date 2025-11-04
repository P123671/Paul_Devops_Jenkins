pipeline {
    agent any
 
    tools {
        maven 'Maven'  // Name from Jenkins -> Global Tool Configuration
    }
 
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YOUR_USERNAME/devops-lab.git'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
 
        stage('Checkstyle') {
            steps {
                // Run Checkstyle analysis
                sh 'mvn checkstyle:checkstyle'
            }
            post {
                always {
                    // Archive the Checkstyle report so you can view it in Jenkins
                    recordIssues tools: [checkStyle(pattern: '**/target/checkstyle-result.xml')]
                }
                failure {
                    echo '❌ Checkstyle found code style issues!'
                }
                success {
                    echo '✅ Checkstyle passed with no issues!'
                }
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
            echo '✅ Build, tests, checkstyle, and packaging completed successfully!'
        }
        failure {
            echo '❌ Build failed. Check logs or test results for details.'
        }
    }
}
