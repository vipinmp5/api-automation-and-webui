package com.ataccama.testutil;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import org.testng.Assert;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by neodevan
 */
public class TestUtils {

	// Verify the http response status returned. Check Status Code is 200?
	public void checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}
	
	public void verifyCalculation(String actual, String Expected) {
		Assert.assertEquals(actual, Expected, "Actual: "+actual+" is Not Matching Expected: "+Expected);
	}
	
	public void verifySOAPResponse(String actual, String Expected) {
		Assert.assertTrue(actual.contains(Expected));
	}
	
	

	public void checkBodyContent(Response res, String Result) {
		ResponseBody body = res.getBody();
		String bodyStringValue = body.asString();
		Assert.assertTrue(bodyStringValue.contains(Result));

	}
	
	public static File writeAndReadFromFile(String Operator, String Val1, String  Val2) throws SAXException, ParserConfigurationException, IOException, TransformerException, InterruptedException{
		String filePath=System.getProperty("user.dir")+"//Requests//MyRequest.xml";
		int val1=Integer.parseInt(Val1);
    	int val2=Integer.parseInt(Val2);
		String xmlInput =
				" <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testcalc.qa.ataccama.com/\">\n" +
				" <soapenv:Header/>\n" +
				" <soapenv:Body>\n" +
				" <soap:"+Operator+">\n" +
				" <val1>" + val1 + "</val1>\n" +
				" <val2>" + val2 + "</val2>\n" +
				" </soap:"+Operator+">\n" +
				" </soapenv:Body>\n" +
				" </soapenv:Envelope>";
		stringToDomFormal(xmlInput, filePath);
		File requestFile=new File(filePath);
		return requestFile;
	}
	
	
	public static void stringToDomFormal(String xmlSource, String filePath) 
	        throws SAXException, ParserConfigurationException, IOException, TransformerException {
		// Parse the given input
		java.io.FileWriter fw = new java.io.FileWriter(filePath);
	    fw.write(xmlSource);
	    fw.close();
	}

}
