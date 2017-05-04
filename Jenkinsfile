node {
   def serverArti = Artifactory.server 'CWDS_DEV'
   def rtGradle = Artifactory.newGradleBuild()
   properties([disableConcurrentBuilds(),[$class: 'BuildDiscarderProperty',strategy: [$class: 'LogRotator', numToKeepStr: '3']]])

   catchError {
        deleteDir()
   } 

   
   stage('Preparation') {
		  git url: 'https://github.com/ca-cwds/cals-api.git'
		  rtGradle.tool = "Gradle_35"
		  rtGradle.resolver repo:'repo', server: serverArti

		  
   }
   stage('Build'){
		def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'jar'
   }
    stage('Test') {
		buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'test'
    }
   stage('SonarQube analysis'){
		withSonarQubeEnv('Core-SonarQube') {
			buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'sonarqube'
		}
    }
	stage ('Push to artifactory'){
	    rtGradle.deployer repo:'libs-snapshot', server: serverArti
	    rtGradle.deployer.deployArtifacts = true
		buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'artifactoryPublish'
	}
	
	stage('Clean WorkSpace') {
		archiveArtifacts artifacts: '**/cals-api*.jar,readme.txt', fingerprint: true
		deleteDir()
	}
}