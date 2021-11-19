package com.qastack.regression;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class SampleUpdateTest {
	
	
	static String projectId="10002";
	static	String versionId="-1";
	static	String cycleId="4";
	static	String url="http://localhost:8080/";
	
	
	public static void main (String [] args){
		
		String issueKey="QAS-1";
		//Fecth issueid from issue key 
		RequestSpecification issueIdRequest=RestAssured.given();
		issueIdRequest.baseUri(url);
		issueIdRequest.header("Content-Type","application/json");
		issueIdRequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String issueEndpoint="rest/api/latest/issue/"+issueKey;
		String issueId=issueIdRequest.get(issueEndpoint).getBody().jsonPath().get("id");
		
		System.out.println(issueId);
		
		//Create excution id 
		
		RequestSpecification execIdRequest=RestAssured.given();
		execIdRequest.baseUri(url);
		execIdRequest.header("Content-Type","application/json");
		execIdRequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String execIdEndpoint="rest/zapi/latest/execution";
		HashMap<String , String > body=new <String , String>HashMap();
		body.put("issueId", issueId);
		
		body.put("versionId", versionId);
		body.put("cycleId", cycleId);
		body.put("projectId", projectId);
		execIdRequest.body(body);
		
		String execId=execIdRequest.post(execIdEndpoint).getBody().asString().split(":")[0].replace("{", "").replace("\"", "");
		System.out.println(execId);
		
		
		//Updating status 
		RequestSpecification updateRequest=RestAssured.given();
		updateRequest.baseUri(url);
		updateRequest.header("Content-Type","application/json");
		updateRequest.header("Authorization","Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
		String updateEndpoint="rest/zapi/latest/execution/"+execId+"/execute";
		
		HashMap<String , String > statusBody=new <String , String>HashMap();
		statusBody.put("status", "2");
		
		updateRequest.body(statusBody);
		
		int status= updateRequest.put(updateEndpoint).getStatusCode();
		System.out.println(status);
		
		
	}

}
