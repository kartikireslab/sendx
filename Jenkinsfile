pipeline {
  agent none
  stages {
    stage('Maven Install') {
      agent {
	       steps {
		sh 'docker login'
     	 }
        docker {sh 'docker login'
          image 'maven:3.5.0'
        }
      }
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
