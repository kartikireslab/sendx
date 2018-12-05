pipeline {
  agent none
  stages {
    stage('Maven Install') {
      steps {
		echo 'Making build.'
		sh 'mvn clean install'
      }
    } 
  stage('Docker Build') {
      agent any
      steps { 
        sh 'docker build -t sendx:latest .'
      }
    }
  }
}
