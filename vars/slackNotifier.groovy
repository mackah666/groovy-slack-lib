#!/usr/bin/env groovy
import java.net.URLEncoder

def call(String buildResult, String changeLog) {
  
  changeLog =  { URLDecoder.decode(changeLog) }
  
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
