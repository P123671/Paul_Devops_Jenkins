pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/<your-user>/<your-repo>.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Compiling source code...'
                sh 'sleep 2'
            }
        }
        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'sleep 1'
                echo 'All tests passed!'
            }
        }
    }
}
