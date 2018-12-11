pipeline {
  agent none
  stages {
    stage('Maven Install') {
	agent {
        	docker {
          		image 'maven:3.5.0'
        	}
      	}
      steps {
		echo 'Making build.'
		sh 'mvn clean install'
	        sh 'scp -r /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar /usr/share/tomcat8/.jenkins/workspace/Sendx/sendx-0.0.1-SNAPSHOT.jar'
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
