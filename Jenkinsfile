pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/P123671/Paul_Devops_Jenkins.git'
            }
        }

        stage('Debug Structure') {
            steps {
                sh '''
                    echo "=== Checking exact file structure ==="
                    pwd
                    find . -name "*.java" -type f
                    echo "=== Checking test file content ==="
                    cat src/test/java/com/example/AppTest.java || echo "Test file not found!"
                    echo "=== Checking package info ==="
                    head -5 src/test/java/com/example/AppTest.java
                    echo "=== Running maven with more verbose output ==="
                    mvn test-compile -X | grep -i "test" || echo "No test-related output"
                '''
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
                    junit 'target/surefire-reports/*.xml'
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
            echo '✅ Build, test, and packaging completed successfully!'
        }
        failure {
            echo '❌ Build failed. Check the console output or test results.'
        }
    }
}
