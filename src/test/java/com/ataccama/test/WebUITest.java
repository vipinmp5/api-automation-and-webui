package com.ataccama.test;

import org.testng.annotations.Test;


public class WebUITest extends BaseTest {
	
	 @Test(dataProvider="TestData")
	    public void verifyTestCalculateUI(String Operator, String Val1, String  Val2, String Result) {
		 testUtils.verifyCalculation(objWebPage.Calculate(Val1, Val2, Operator), Result);
	 }

}
