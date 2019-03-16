package com.ataccama.test;


import org.testng.annotations.*;

import com.ataccama.utils.ApiUtils;

/**
 * Created by Vipin Alias Neo De Van
 */
public class RestApiPostTest extends BaseTest{

    @Test(dataProvider="TestData")
    public void verifyPostResponseTest(String Operator, String Val1, String Val2, String Result) {
    	int val1=Integer.parseInt(Val1);
    	int val2=Integer.parseInt(Val2);
        res = ApiUtils.postResponsebyBody(Operator,val1,val2);
        testUtils.checkStatusIs200(res);
        testUtils.checkBodyContent(res, Result);
    }
    
}
