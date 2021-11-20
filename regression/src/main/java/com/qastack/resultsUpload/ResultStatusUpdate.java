package com.qastack.resultsUpload;

import java.io.File;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ResultStatusUpdate {

	
	static String url="http://localhost:8080/";
	static String projectId="10002";
	static String versionId="-1";
	static String issueKey="QAS-1";
	static String cycleId="6";
	
	public static void updateZephyrResults(String issueKey, String status, String filePath) {
		// Fetch issue id from issue key
		RequestSpecification issueIdrequest= RestAssured.given();
		
		issueIdrequest.baseUri(url);
		issueIdrequest.header("Content-Type","application/json");
		issueIdrequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String issueEndpoint="/rest/api/latest/issue/"+issueKey;
		String issueId=issueIdrequest.get(issueEndpoint).getBody().jsonPath().get("id").toString();
		System.out.println(issueId);
		
		// Create execution Id 
		RequestSpecification execIdrequest= RestAssured.given();
		execIdrequest.baseUri(url);
		execIdrequest.header("Content-Type","application/json");
		execIdrequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String execEndpoint="rest/zapi/latest/execution/";
		HashMap<String ,String> requestBody= new HashMap<String ,String> ();
		requestBody.put("issueId", issueId);
		requestBody.put("cycleId", cycleId);
		requestBody.put("versionId", versionId);
		requestBody.put("projectId", projectId);
		execIdrequest.body(requestBody);
		
//		int statusCode =execIdrequest.post(execEndpoint).getStatusCode();
		String executionId=execIdrequest.post(execEndpoint).getBody().asString().split(":")[0].replace("\"", "").replace("{", "");
		System.out.println(executionId);
		
		
		//Update the status
		RequestSpecification updaterequest= RestAssured.given();
		updaterequest.baseUri(url);
		updaterequest.header("Content-Type","application/json");
		updaterequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String updateEndpoint="rest/zapi/latest/execution/"+executionId+"/execute";
		HashMap<String ,String> updateReqBody= new HashMap<String ,String> ();
		updateReqBody.put("status", status);
		updaterequest.body(updateReqBody);
		int statusCode =updaterequest.put(updateEndpoint).getStatusCode();
		System.out.println(statusCode);
		
		//UploadAttachment
		
		RequestSpecification uploadRequest= RestAssured.given();
		uploadRequest.baseUri(url);
		uploadRequest.header("Content-Type","multipart/form-data");
		uploadRequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String uploadEndpoint="rest/zapi/latest/attachment/";
		uploadRequest.multiPart("file",new File(filePath),"text/plain");
		uploadRequest.queryParam("entityId",executionId);
		uploadRequest.queryParam("entityType","execution");
		
		int uploadStatus=uploadRequest.post(uploadEndpoint).getStatusCode();
		System.out.println(uploadStatus);
	}

}
