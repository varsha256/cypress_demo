package com.qastack.regression;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
public class UpdateZephyrTests {

	public static void main (String [] args) throws ParseException {
		String projectId="10002";
        String cycleId="3";
        String versionId="-1"; 
		String issueStatus ="PASSED";
		String issueKey="QAS-1";
		String baseUri="http://localhost:8080";
		String getIssueIdEndPoint="/rest/api/latest/issue/" + issueKey + "/";
		String createExecutionIdEndPoint="/rest/zapi/latest/execution";
		uploadAttachment("4","C:\\Users\\Lenovo\\info.txt");
		
		
//		zephyrUpdate("QAS-2","FAILED");
		
	}
	




	   public static RequestSpecification request;
	    public static UpdateZephyrTests testCaseStatusUpdate;

	    public static void updateAllZephyrIssues() {
//	        try {
//	            FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "\\ExecutionResults\\Results.xlsx"));
//	            //creating workbook instance that refers to .xls file
//	            XSSFWorkbook wb = new XSSFWorkbook(fis);
//	            //creating a Sheet object to retrieve the object
//	            XSSFSheet sheet = wb.getSheetAt(0);
//	            //evaluating cell type
//
//
//	            FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
//	            int statusColIndex = 3;
//	            int scenarioIndex = 2;
//	            int issueKeyIndex = 1;
//	            int updateFlagIndex = 4;
//	            int rowIndex = 1;
//	            int colind = 1;
//	            int lastRow = sheet.getLastRowNum();
//	            for (int rowitr = 1; rowitr < lastRow; rowitr++) {
//
//	                String tcStatus = sheet.getRow(rowitr).getCell(statusColIndex).getStringCellValue();
//	                String scenarioName = sheet.getRow(rowitr).getCell(scenarioIndex).getStringCellValue();
//	                String zephyrIssueKey = sheet.getRow(rowitr).getCell(issueKeyIndex).getStringCellValue();
//	                String updateZephyrFlag = sheet.getRow(rowitr).getCell(updateFlagIndex).getStringCellValue();
//	                String issueId = getIssueIdFromeIssueKey(zephyrIssueKey);
//	                System.out.println("issueId" + issueId);
//	                String executionId = createExecutionId(issueId, "-1", "12508", "11329");
//	                updateZephyrStatus(executionId, tcStatus);
//	            }
//	            wb.close();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }

	    }
    public  static void zephyrUpdate(String issueKey, String status) throws ParseException{
	            String issueStatus =status;
	            System.out.println(issueStatus+"issueStatus");
	            String issueId = testCaseStatusUpdate.getIssueIdFromeIssueKey(issueKey);
	            System.out.println("issueId"+issueId);
	            String projectId="10002";
	            String cycleId="3";
	            String versionId="-1";
	            String executionId = createExecutionId(issueId, versionId, cycleId, projectId);
	            updateZephyrStatus(executionId,issueStatus);
	            uploadAttachment(executionId,"C:\\info.txt");
	            


	       }
	    public static String getIssueIdFromeIssueKey(String issueKey) {
	        System.out.println("************Fecthing Issue id from issue key *****************");
	        String url = "http://localhost:8080";
	        String postUrl = "/rest/api/latest/issue/" + issueKey + "/";
	        RequestSpecification request = RestAssured.given();
	        request.baseUri(url);
	        request.relaxedHTTPSValidation();
	        request.header("Content-Type", "application/json");
	        request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	        String issueId = request.get(postUrl).getBody().jsonPath().get("id");
	        System.out.println(issueId);
	        System.out.println("************Fecthing Issue id from issue key end*****************");

	        return issueId;
	        
	    }

	    public static void updateZephyrStatus(String executionId, String status) {
	        System.out.println("************Updating Execution id for issue key-"+executionId+" *****************");

	        String url = "http://localhost:8080";
	        String postUrl = "/rest/zapi/latest/execution/" + executionId + "/execute";
	        int stat = 0;
	        
	        

	        RequestSpecification request = RestAssured.given();
	        request.baseUri(url);
	        request.relaxedHTTPSValidation();
	        request.header("Content-Type", "application/json");
	        request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	        String statusPaylod = "{\"status\":\"" + status + "\"}";
	        request.body(statusPaylod);
	        String statusCode = request.put(postUrl).getBody().asPrettyString();
	        System.out.println(stat);
	        System.out.println("************Updating Execution id for issue key end *****************");


	    }

	    public static String createExecutionId(String issueId, String versionId, String cycleId, String projectid) throws ParseException {
	        System.out.println("************Creating Execution id for issue key *****************");

	    	String url = "http://localhost:8080";
	        String postUrl = "/rest/zapi/latest/execution";
	        int intIssueId = Integer.parseInt(issueId);
	        String executionid;
	        RequestSpecification request = RestAssured.given();
	        request.baseUri(url);
	        request.relaxedHTTPSValidation();
	        request.header("Content-Type", "application/json");
	        request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	        HashMap body = new HashMap();
	        body.put("issueId", intIssueId);
	        body.put("projectId", projectid);
	        body.put("cycleId", cycleId);
	        body.put("versionId", versionId);
	        request.body(body);
	        String  Response =request.post(postUrl).getBody().asString();
	     	String substring= Response.split(":")[0];
	       
	        executionid=substring.replace("{", "");
	        executionid=executionid.replace("\"", "");
			System.out.println("************Creating Execution id for issue key end "+executionid+"*****************");
	       
	        return executionid;
	    }
	    
	    
	    
	    public static void uploadAttachment(String executionId, String filepath) {
	        System.out.println("************Uploading Attachment issue key "+executionId+" *****************");

	        String url = "http://localhost:8080";
	        String postUrl = "/rest/zapi/latest/attachment/";
	        

	        RequestSpecification request = RestAssured.given();
	        request.baseUri(url);
	        request.relaxedHTTPSValidation();
	        
	        request.header("Content-Type", "multipart/form-data");
	        request.header("Authorization", "Basic dmFyc2hheWFkYXY6U0BAajI1NjkwMjM=");
	        request.header("Accept", "*/*");
	        request.header("Accept-Encoding", "gzip, deflate, br");
	        request.multiPart("file",new File(filepath),"text/plain");
	        request.queryParam("entityId", executionId);
	        request.queryParam("entityType", "execution");
//	        request.body("{}");
	        int statusCode = request.post(postUrl).getStatusCode();
	        System.out.println("************Uploading Attachment for issue key end "+statusCode+" *****************");


	    }
	        
	
	    
	    
	    
	}


