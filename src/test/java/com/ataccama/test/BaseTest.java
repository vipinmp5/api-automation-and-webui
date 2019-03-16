package com.ataccama.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.ataccama.pages.WebUIPage;
import com.ataccama.testutil.TestUtils;
import com.ataccama.utils.ApiUtils;
import com.ataccama.utils.BrowserFactory;
import com.ataccama.utils.TestDataReader;
import com.jayway.restassured.response.Response;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Created by Vipin Alias Neo De Van
 */
public class BaseTest {

	protected Logger log=Logger.getLogger(BaseTest.class);
    public Response res = null; //Response
    String BaseWebURL = "http://localhost:8081/testCalc/webUI";
    TestUtils testUtils = new TestUtils();
    WebDriver driver;
    WebUIPage objWebPage;

    @BeforeTest
    public void setup (){
        ApiUtils.setBaseURI();
        driver = BrowserFactory.InitBrowser("Chrome");
        driver.get(BaseWebURL);
        objWebPage = new WebUIPage(driver);
    }

    @AfterTest
    public void afterTest (){
        ApiUtils.resetBaseURI();
        ApiUtils.resetBasePath();
        driver.close();
        driver.quit();
    }
    
    @DataProvider(name="TestData") 
    public Object[][] getData(Method method)
    {
        log.info("Reading test data for : " +method.getName());
        String currentTestClass=method.getDeclaringClass().toString();
        currentTestClass=currentTestClass.substring(currentTestClass.lastIndexOf(".")+1, currentTestClass.length());
        return TestDataReader.getDataFromExcelFile(currentTestClass,method.getName());
    }
    

}
