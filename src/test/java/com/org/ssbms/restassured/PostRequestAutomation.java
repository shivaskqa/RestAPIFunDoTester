package com.org.ssbms.restassured;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PostRequestAutomation {
	
	@Test(enabled=false)
	public void testPostRequestWithStringPayload()
	{
		RestAssured.baseURI="https://reqres.in/api/users";
		
		String body="{\r\n"
				+ "    \"name\": \"Eeshaan Skandha\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		RestAssured.given()
		           .body(body)
		           .when()
		           .post()
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(201);
		
		
	}

	@Test
	public void testPostRequestWithHashMap()
	{
		Map<String,String> dataMap=new HashMap<>();
		dataMap.put("name", "Rohit");
		dataMap.put("job", "Cricketer");
		
		RequestSpecification request=new RequestSpecBuilder().build();
		
		request.baseUri("https://reqres.in/api/users");
		request.accept(ContentType.JSON);
		request.contentType(ContentType.JSON);
		request.body(dataMap);
		
		RestAssured.given().spec(request).when().post().then().log().all().statusCode(201);
	}
}
