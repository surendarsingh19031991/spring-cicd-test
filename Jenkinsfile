node {
  stage('SCM') {
    checkout scm
  }

  stage('Build & SonarQube Analysis') {
    def mvn = tool 'Default Maven'
    def javaHome = tool 'jdk21'
    env.JAVA_HOME = javaHome
    env.PATH = "${javaHome}/bin:${env.PATH}"

    withSonarQubeEnv('SonarQube') {
      sh """
        java -version
        ${mvn}/bin/mvn clean verify sonar:sonar \
        -Dsonar.projectKey=springboot-scan \
        -Dsonar.projectName=springboot-scan
      """
    }
  }

  stage('Quality Gate') {
    timeout(time: 2, unit: 'MINUTES') {
      waitForQualityGate abortPipeline: true
    }
  }
}

