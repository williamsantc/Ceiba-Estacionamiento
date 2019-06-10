pipeline {
    //Donde se va a ejecutar el Pipeline  
	agent {    
		label 'Slave_Induccion'  
	}  
	//Opciones específicas de Pipeline dentro del Pipeline  
	options {
		//Mantener artefactos y salida de consola para el # específico de ejecucionesrecientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '3'))
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()  
	}
		//Una sección que define las herramientas para “autoinstalar” y poner en la PATH  
		tools {    
		jdk 'JDK8_Centos' //Preinstalada en la Configuración del Master    
		gradle 'Gradle5.0_Centos' //Preinstalada en la Configuración del Master  
	}
	//Aquí comienzan los “items” del Pipeline  
	stages {    
		stage('Checkout') {      
			steps{        
				echo "------------>Checkout<------------"
				checkout([$class: 'GitSCM', branches: [[name: '*/master']],doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'Github_williamsantc', url:'https://github.com/williamsantc/Ceiba-Estacionamiento']]])
			}    
		}    
		stage('Unit Tests') {      
			steps{        
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle test'  
			}    
		}    
		stage('Integration Tests') {      
			steps {        
				echo "------------>Integration Tests<------------"      
			}    
		}    
		stage('Build') {
		    steps {
		        echo '------------>Build<------------'
		        sh 'gradle --b ./build.gradle build -x test'
		    }
		}
		stage('Static Code Analysis') {      
			steps{        
				echo '------------>Análisis de código estático<------------'        
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
				}
			}
		}
	}
	
	post {
	
	    failure {
    	    mail(to: 'william.santos@ceiba.com.co',
				body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
				subject: "ERROR CI: ${env.JOB_NAME}")
    	}
	}
}