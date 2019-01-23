#!/usr/bin/env groovy

def call(String buildResult, String changeLog) {
  
  changelog = changelog.replace("\n", "\\n")
  changelog = changelog.replace("\t", "\\t")
  changelog = changelog.replace("\"", '\\"')
  changelog = changelog.replace("&", "&amp;")
  changelog = changelog.replace("<", "&lt;")
  changelog = changelog.replace(">", "&gt;")

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
