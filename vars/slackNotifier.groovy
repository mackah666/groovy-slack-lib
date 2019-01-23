#!/usr/bin/env groovy

def call(String buildResult, String changeLog) {
  
  changeLog = changeLog.replace("\n", "\\n")
  changeLog = changeLog.replace("\t", "\\t")
  changeLog = changeLog.replace("\"", '\\"')
  changeLog = changeLog.replace("&", "&amp;")
  changeLog = changeLog.replace("<", "&lt;")
  changeLog = changeLog.replace(">", "&gt;")

  if ( buildResult == "SUCCESS" ) {
    slackSend color: "good", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} at Url: ${env.BUILD_URL} was successful ChangeLog: $changeLog"
  }
  else if( buildResult == "FAILURE" ) { 
    slackSend color: "danger", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} at Url: ${env.BUILD_URL} was failed"
  }
  else if( buildResult == "UNSTABLE" ) { 
    slackSend color: "warning", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} at Url: ${env.BUILD_URL} was unstable"
  }
  else {
    slackSend color: "danger", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER} at Url: ${env.BUILD_URL} its result was unclear"	
  }
}
