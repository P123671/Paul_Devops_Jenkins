pipeline {
    agent any
 
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
 
        stage('Checkout') {
            steps {
                git branch: 'DayThreeMavenExercise', url: 'https://github.com/P123671/Paul_Devops_Jenkins'
            }
        }
 
        stage('Build') {
            steps {
                echo '⚙️ Building the project...'
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
                    // Publish test results in Jenkins UI
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    echo '❌ Some tests failed! Check Jenkins Test Report tab.'
                }
            }
        }
 
        stage('Package') {
            steps {
                echo '📦 Packaging the application...'
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo '✅ All stages completed successfully — build and tests passed!'
        }
        failure {
            echo '💥 Pipeline failed — check which stage failed above.'
        }
    }
}
