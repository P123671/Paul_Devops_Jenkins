pipeline {
    agent any
    parameters {
        string(name: 'DEPLOY_ENV', defaultValue: 'dev', description: 'Environment to deploy')
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Deploy') {
            when {
                expression { params.DEPLOY_ENV == 'dev' }
            }
            steps {
                echo "Deploying to ${params.DEPLOY_ENV} environment"
            }
        }
    }
}
