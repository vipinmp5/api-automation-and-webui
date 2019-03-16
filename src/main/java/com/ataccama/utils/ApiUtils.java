package com.ataccama.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;






import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.entity.InputStreamEntity;


/**
 * Created by Vipin Alias Neo De Van
 */
public class ApiUtils {
	// Global Setup Variables
	public static String path;
	public static String jsonPathTerm;
	static String endpoint="http://localhost:8081/testCalc/soapWS?wsdl";

	// Sets Base URI
	public static void setBaseURI() {
		RestAssured.baseURI = "http://localhost:8081/testCalc/";
	}

	
	// Sets base path
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	// Sets ContentType
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	// Returns response by given path
	public static Response getResponsebyQueryParam(String pathOperation, int val1, int val2) {
		return given().accept("application/json").queryParam("val1", val1).queryParam("val2", val2).when().get("restWS/{pathOperation}",pathOperation);
	}
	
	public static Response postResponsebyBody(String pathOperation, int val1, int val2) {
		return given().contentType(ContentType.JSON).body("{\"val1\": "+val1+", \"val2\": "+val2+", \"operation\": \""+pathOperation+"\"}").when().post("restWS/compute");
	}

	// Returns response
	public static Response getResponse() {
		return get();
	}
	
	public static String SOAPRequest(File requestFile, String Operator) throws FileNotFoundException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post= new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-Type","text/xml");
		
		HttpResponse response=client.execute(post);
		BufferedReader br= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		StringBuffer sb=new StringBuffer();
		while((line=br.readLine())!=null){
			sb.append(line);
		}
		
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//Response//Response"+Operator+".xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
		return sb.toString();
	}
	

}