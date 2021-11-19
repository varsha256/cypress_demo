package com.qastack.regression;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestUpdateZephyr {
	
	static String projectId="10002";
	static	String versionId="-1";
	static String cycleId="3";
	static String baseUri="http://localhost:8080/";
	static String issuekey="QAS-1";
	static String status="PASSED";
	public static void main(String args[]){
	
		//Fetch issueid from issuekey
	
	RequestSpecification issueId_request=RestAssured.given();
	issueId_request.baseUri(baseUri);
	issueId_request.header("Content-Type","application/json");
	issueId_request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	String endpointIssue="/rest/api/latest/issue/"+issuekey;
	String issueid=issueId_request.get(endpointIssue).getBody().jsonPath().get("id");
	System.out.println(issueid);
	
	//create excution id
	RequestSpecification execution_request=RestAssured.given();
	execution_request.baseUri(baseUri);
	execution_request.header("Content-Type","application/json");
	execution_request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	String endpointExecution="/rest/zapi/latest/execution/";
	HashMap<String, String> body=new HashMap<String, String> ();
	body.put("issueId", issueid);
	body.put("versionid",versionId);
	body.put("cycleId",cycleId);
	body.put("projectId", projectId);
	execution_request.body(body);
	
	String executionId=execution_request.post(endpointExecution).getBody().asString().split(":")[0].replaceAll("\"", "").replace("{", "");
	System.out.println(executionId);
	
	//update status 
	
	RequestSpecification status_request=RestAssured.given();
	status_request.baseUri(baseUri);
	status_request.header("Content-Type","application/json");
	status_request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	String endpointUpdate="/rest/zapi/latest/execution/"+executionId+"/execute";
	HashMap<String, String> body2=new HashMap<String, String> ();
	body2.put("status", "1");
	
	status_request.body(body2);
	int statuscode=status_request.put(endpointUpdate).getStatusCode();
	System.out.println(statuscode);
}}
