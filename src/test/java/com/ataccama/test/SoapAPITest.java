package com.ataccama.test;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.testng.annotations.*;
import org.xml.sax.SAXException;

import com.ataccama.testutil.TestUtils;
import com.ataccama.utils.ApiUtils;


public class SoapAPITest extends BaseTest {
	
	
	@Test(dataProvider="TestData")
	public void verifySOAPAPIResponse(String Operator, String Val1, String  Val2, String Result) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException, TransformerException, InterruptedException{
		
		File requestFile = TestUtils.writeAndReadFromFile(Operator, Val1, Val2);
		testUtils.verifySOAPResponse(ApiUtils.SOAPRequest(requestFile, Operator),Result);
		
	}

}
