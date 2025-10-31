pipeline {
    agent any
 

    // Optional: declare environment variables available during run
    environment {
        APP_NAME = 'my-app'
        BUILD_OUTPUT = "build-artifact-${env.BUILD_NUMBER}.tar.gz"
        // Example of using credentials binding (if needed):
        // GITHUB_TOKEN = credentials('github-token') 
    }

 

    // Optional build parameters (students can trigger with different branches)
    parameters {
        string(name: 'BUILD_BRANCH', defaultValue: 'main', description: 'Branch to build')
        booleanParam(name: 'RUN_DEPLOY', defaultValue: false, description: 'Simulate deploy after success')
    }

 

    options {
        // Keep builds for a while, prevent concurrent builds of the same branch
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        // If using multibranch, Jenkins may check out automatically; we do explicit checkout below
    }

 

    stages {
        stage('Checkout') {
            steps {
                // Checkout the selected branch (parameter) - uses credentials if needed
                script {
                    def branchToUse = params.BUILD_BRANCH ?: 'main'
                    echo "Requested branch: ${branchToUse}"
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "refs/heads/${branchToUse}"]],
                        userRemoteConfigs: [[url: 'https://github.com/P123671/Paul_Devops_Jenkins.git', /* credentialsId: 'github-token' */]]
                    ])
                }
            }
        }

 

        stage('Enforce main') {
            when {
                expression { return env.GIT_BRANCH == null ? (params.BUILD_BRANCH == 'main') : env.GIT_BRANCH.contains('main') || params.BUILD_BRANCH == 'main' }
            }
            steps {
                echo "Building only because this is main (or BUILD_BRANCH == main)"
            }
        }

 

        stage('Build') {
            when { expression { return params.BUILD_BRANCH == 'main' } }
            steps {
                sh 'echo "Compiling app..."'
                // Replace with real build command, e.g. mvn -B -DskipTests package
                sh 'sleep 1'
            }
        }

 

        stage('Test') {
            when { expression { return params.BUILD_BRANCH == 'main' } }
            steps {
                // Example: run unit tests (replace with your test runner)
                sh 'echo "Running unit tests..."'
                // Simulate a test runner that writes junit xml to target/test-results/*.xml
                sh '''
                   mkdir -p test-results
                   echo "<testsuites><testsuite name=\\"demo\\"><testcase classname=\\"a\\" name=\\"t1\\"/></testsuite></testsuites>" > test-results/results.xml
                '''
                // Archive the JUnit XML so Jenkins can parse it
                junit 'test-results/*.xml'
            }
        }

 

        stage('Package') {
            when { expression { return params.BUILD_BRANCH == 'main' } }
            steps {
                sh 'echo "Packaging artifact..."'
                sh "tar -czf ${env.BUILD_OUTPUT} * || true"
                archiveArtifacts artifacts: "${env.BUILD_OUTPUT}", fingerprint: true
            }
        }

 

        stage('Deploy (Simulated)') {
            when { allOf { expression { return params.RUN_DEPLOY == true }; expression { return params.BUILD_BRANCH == 'main' } } }
            steps {
                sh 'echo "Simulating deploy to staging..."'
                sh 'sleep 1'
            }
        }
    } // stages

 

    post {
        success {
            echo "SUCCESS: Build ${env.BUILD_NUMBER} for ${params.BUILD_BRANCH}"
        }
        failure {
            echo "FAILURE: Build ${env.BUILD_NUMBER} for ${params.BUILD_BRANCH}"
            // Email (Email Extension plugin)
            emailext (
                subject: "Jenkins: Build FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Build <a href="${env.BUILD_URL}">${env.JOB_NAME} #${env.BUILD_NUMBER}</a> FAILED.
                        <br/>Branch: ${params.BUILD_BRANCH}
                        <br/>See console output: ${env.BUILD_URL}console""",
                to: "paul.khmaladze@gmail.com"
            )

 

            // Slack (Slack plugin) — optional, requires plugin + config
            // slackSend(channel: '#your-channel', message: "Build FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|console>)")
        }
        unstable {
            echo "UNSTABLE: Build ${env.BUILD_NUMBER}"
        }
        always {
            echo "CLEANUP & FINISH"
            // e.g. delete temporary files, or show workspace list
            sh 'echo "Workspace contents:"; ls -la || true'
        }
    }
}
