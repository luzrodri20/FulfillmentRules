@Library('openshift-library') _

openshift.withCluster() {
  String ocpApiServer = env.OCP_API_SERVER ? "${env.OCP_API_SERVER}" : "https://openshift.default.svc.cluster.local"
  env.NAMESPACE = openshift.project()
  env.TOKEN = readFile('/var/run/secrets/kubernetes.io/serviceaccount/token').trim()
  //env.OC_CMD = "oc --token=${env.TOKEN} --server=${ocpApiServer} --certificate-authority=/run/secrets/kubernetes.io/serviceaccount/ca.crt --namespace=${env.NAMESPACE}"
  //env.APP_NAME = "${env.JOB_BASE_NAME}".replaceAll(/-?pipeline-?/, '').replaceAll(/-?${env.NAMESPACE}-?/, '')
  env.APP_NAME = "fulfillment-rules"
  echo "Starting Pipeline for ${APP_NAME}"
  
  def projectBase = "${env.NAMESPACE}".replaceAll(/-build/, '') //or replaceAll(/-sbx/, '')


  echo "projectBase is ${projectBase}"

  env.DVL_ENV = "${projectBase}"
  env.BUILD_ENV = "${projectBase}-build"
  env.DEPLOY_ENV = "${projectBase}-dvl"
  env.ARTIFACT_DIRECTORY = "./target"
  env.NEXUS_USERNAME="SIvQPqmC"
  env.NEXUS_PASSWORD ="pIG/mYzSslxJiCe6uNq8/nci9ihw2Ge4QXwJf2PLD784"
}

// def gradleCmd = './gradlew'    

pipeline {

  agent { label 'maven' }

  stages {

    stage('SCM Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        withCredentials([[$class: 'UsernamePasswordMultiBinding' , credentialsId: "${NAMESPACE}-nexus-upload-token",
                       usernameVariable: "username", passwordVariable: "password"]]){
	   echo "Maven user name is ${NEXUS_USERNAME} and password is ${NEXUS_PASSWORD}"
           sh 'mvn -s config/settings.xml -f pom-dvl.xml -B -U -DskipTests=true clean deploy'
	}
      }
    }

  }
}
