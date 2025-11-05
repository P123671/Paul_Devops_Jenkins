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
                // Use the JaCoCo Maven plugin to run tests with coverage
                sh 'mvn test jacoco:report'
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
        always {
            // Always publish JaCoCo coverage report, even if build fails
            jacoco(
                execPattern: 'target/jacoco.exec',
                classPattern: 'target/classes',
                sourcePattern: 'src/main/java',
                // Optional: Set coverage thresholds (uncomment if needed)
                // changeBuildStatus: true,
                // minimumInstructionCoverage: '70', 
                // minimumBranchCoverage: '60',
                // minimumComplexityCoverage: '60',
                // minimumLineCoverage: '70',
                // minimumMethodCoverage: '70',
                // minimumClassCoverage: '80'
            )
        }
        success {
            echo '✅ All stages completed successfully — build and tests passed!'
        }
        failure {
            echo '💥 Pipeline failed — check which stage failed above.'
        }
    }
}
