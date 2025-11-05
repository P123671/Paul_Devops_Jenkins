pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
        stage('Checkout') {
            steps {
                git branch: 'DayThreeJunitexercise', url: 'https://github.com/P123671/Paul_Devops_Jenkins.git'
            }
        }
 
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Run Unit Tests') {
            steps {
                echo '🧪 Running JUnit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    echo '❌ Some tests failed. Check Jenkins Test Report.'
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
            echo '✅ All tests passed! Build successful.'
        }
        failure {
            echo '❌ Build failed due to test errors.'
        }
    }
}
