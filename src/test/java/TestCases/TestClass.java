package test.java.TestCases;

import PageWiseActions.HomePageActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonFunctions.BaseTest;

public class TestClass extends BaseTest {



    @Test
    public void validateTitle(){
       String actualTitle= driver.getTitle();
       Assert.assertEquals(actualTitle,"GoKwik: Boost Conversion Rates with 100% RTO Protection");
    }
    @Test
    public void validateContactUs(){
        HomePageActions homePageActions= new HomePageActions();
        homePageActions.clickAboutUs();
    }


}
