package com.qastack.regression;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qastack.regression.UpdateZephyrTests;
import com.qastack.resultsUpload.ResultStatusUpdate;


public class Days {
	ITestResult result;

	
	@Test
	public  void today(String v){
		
		Assert.assertTrue(true);
		
	}
	
	@AfterMethod
	public void updateResult(ITestResult result) throws ParseException{
		int stat=result.getStatus();
		System.out.println("status of testcase execution is " +stat);
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			UpdateZephyrTests.updateZephyrStatus("QAS-3","1");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			UpdateZephyrTests.zephyrUpdate("QAS-3","2");
		}
	}
	
	


}
