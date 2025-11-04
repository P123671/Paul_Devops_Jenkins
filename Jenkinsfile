pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'DayTwoJunitHWexercise', url: 'https://github.com/P123671/Paul_Devops_Jenkins.git'
            }
        }

        stage('Format Check') {
            steps {
                echo '🔍 Checking code formatting...'
                sh 'mvn spotless:check'
            }
        }

        stage('Linter Check') {
            steps {
                echo '🧹 Running Checkstyle...'
                sh 'mvn checkstyle:check'
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
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Build completed successfully with clean code and passing tests!'
        }
        failure {
            echo '❌ Build failed! Check which stage failed: formatting, linting, or tests.'
        }
    }
}

