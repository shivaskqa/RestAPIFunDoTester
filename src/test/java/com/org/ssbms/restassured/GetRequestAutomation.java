package com.org.ssbms.restassured;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestAutomation {
	
	@Test
	public void testGetRequest() {
		
		RestAssured.baseURI="https://reqres.in/api/users";
		
		//Here, we are asserting the status code and printing the response on console
		/*
		 * RestAssured.given() .param("page", 2) .when() .get() .then() .assertThat()
		 * .log() .all() .statusCode(200);
		 */
		            
	 //if we want to capture the response then the following code snippet is useful
		Response response=RestAssured.given().param("page", 2).when().get();
		//System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.contentType());
		System.out.println(response.getTime());
		Map<String,String> cookiesMap=response.getCookies();
		System.out.println(cookiesMap);
		System.out.println(response.asPrettyString());
		
		
	}

}
