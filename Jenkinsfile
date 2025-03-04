pipeline {
    agent any

    environment {
        TEST_EMAIL = credentials('TEST_EMAIL_CREDENTIAL')  
        TEST_PASSWORD = credentials('BULLEYSBOSS_PASSWORD')  
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    echo "Checking out the repository..."
                    checkout scm  // Checkout the code from GitHub
                }
            }
        }

        stage('Update Config Properties') {
            steps {
                script {
                    sh '''
                    sed -i "s/emailId = .*/emailId = $TEST_EMAIL/" src/main/java/config/config.properties
                    sed -i "s/password = .*/password = $TEST_PASSWORD/" src/main/java/config/config.properties
                    '''
                }
            }
        }

        stage('Verify Credentials') {
            steps {
                script {
                    echo "Email and Password updated in config.properties (hidden in logs)"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo "Running build process..."
                    mvn clean install
                }
            }
        }
    }
}
