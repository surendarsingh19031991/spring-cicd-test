pipeline {
    agent any

    tools {
        maven 'Default Maven'
        jdk 'JDK_21'
    }

    environment {
        JAVA_21_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        JAVA_17_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    }

    stages {

        stage('Build & Test (Java 21)') {
            steps {
                sh '''
                  echo "==== JAVA CHECK ===="
                  which java
                  java -version
                  echo "==== MVN CHECK ===="
                  which mvn
                  mvn -version
                  mvn clean verify
                '''
            }
        }

        stage('SonarQube Analysis (Java 17)') {
            steps {
                withSonarQubeEnv('Sonar-Server') {
                    sh '''
                      export JAVA_HOME=$JAVA_17_HOME
                      export PATH=$JAVA_HOME/bin:$PATH
                      echo "Java used for Sonar:"
                      java -version

                      sonar-scanner \
                        -Dsonar.projectKey=spring-cicd-test \
                        -Dsonar.projectName=spring-cicd-test \
                        -Dsonar.projectBaseDir=$WORKSPACE \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.java.binaries=target
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
