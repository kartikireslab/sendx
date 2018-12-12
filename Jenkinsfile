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
      }
    } 
  stage('Docker Build') {
      agent any
      steps {
        sh 'docker build -t sendx:latest .'
      }
	  
   Dockerpush('Docker Push')
	  {
	sh  'docker login -u docker030303 -p Abhinav@123Ires'
  	sh  'docker push docker030303/sendx:latest'
      }
    }
  }
}
