package com.org.ssbms.restassured;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PathParametersTest {
	
	
	@Test(enabled=false)
	public void createToken(ITestContext context)
	{
		//https://restful-booker.herokuapp.com/auth
		
		String userCreds="{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		
		String token=(String)RestAssured.given().contentType(ContentType.JSON)
		            .body(userCreds)
		            .when()
		            .post("https://restful-booker.herokuapp.com/auth")
		            .then()
		            .extract()
		            .jsonPath()
		            .get("token");
		System.out.println(token);
		context.setAttribute("token", token);
		
	}
	
	@Test(enabled = false)
	public void testAPIWithPathParameters()
	{

		RestAssured.given()
						.log()
		           		.all()
		           		.baseUri("https://restful-booker.herokuapp.com")
		           		.basePath("{basePath}/{bookingID}")
		           		.pathParam("basePath", "booking")
		           		.pathParam("bookingID", 1)
		           		.accept(ContentType.JSON)
		           .when()
		           		.get()
		           .then()
		           		.log()
		           		.all();
		
	}

	@Test(enabled=false)
	public void testAPIWithGetURLs()
	{

		RestAssured.given()
						.log()
		           		.all()
		           		.pathParam("basePath", "booking")
		           .when()
		           		.get("https://restful-booker.herokuapp.com/{basepath}/{bookingID}",2)
		           .then()
		           		.log()
		           		.all();
		
	}

	@Test(enabled=false)
	public void testCreateBookindID(ITestContext context)
	{
		String jsonPayload="{\r\n"
				+ "    \"firstname\" : \"Vijaya\",\r\n"
				+ "    \"lastname\" : \"Ramakrishnan\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2022-01-01\",\r\n"
				+ "        \"checkout\" : \"2022-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		
		int id=RestAssured.given()
		           .baseUri("https://restful-booker.herokuapp.com")
		           .basePath("/booking")
		           .contentType(ContentType.JSON)
		           .log()
		           .all()
		           .body(jsonPayload)
		           .when()
		           .post()
		           .then()
		           .log()
		           .all()
		           .assertThat()
		           .statusCode(200)
		           .extract()
		           .jsonPath()
		           .getInt("bookingid");
		
		  context.setAttribute("bookingId", id);         
	}
	
	@Test(enabled=false)
	public void testGetWithContext(ITestContext context)
	{
		int bookingID=(Integer)context.getAttribute("bookingId");
		
		RequestSpecification request=RestAssured.given();
		
		request.when()
		       .get("https://restful-booker.herokuapp.com/booking/"+bookingID)
		       .then()
		       .log()
		       .all();
	}
	
	@Test(priority=0)
	public void testUpdateWithContext(ITestContext context)
	{
		//https://restful-booker.herokuapp.com/booking/
		//int bookingId=(Integer)context.getAttribute("bookingId");
		//System.out.println("Booking Id :"+bookingId);
		RequestSpecification request=new RequestSpecBuilder()
				                         .setAccept(ContentType.JSON)
				                         .setBaseUri("https://restful-booker.herokuapp.com")
				                         .setBasePath("/booking/4502")
				                         .build();
		String json="{\r\n"
				+ "    \"firstname\" : \"Mukund\",\r\n"
				+ "    \"lastname\" : \"Menon\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2021-01-01\",\r\n"
				+ "        \"checkout\" : \"2022-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Lunch\"\r\n"
				+ "}";
		
		ResponseSpecification response=new ResponseSpecBuilder()
				                                  .expectStatusCode(200)
				                                  .build();
		
		RestAssured.given()
				.spec(request)
				.body(json)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
					.log().all().when().put().then().log().all();
	}
}
