package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestAPIWithBaseClass extends BaseClass {
	
	RequestSpecification reqSpec;
	private String stringFlightId;
	
	@BeforeClass
	public void beforeClass() {
		
	}
	@Test(priority = 1)
	public void postFlight() throws ParseException {
		addHeader("Content-Type","application/json");
		addPayload("{\r\n"
				+ "    \"flightName\": \"Deutsch\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		Response response = addRequest("POST", "https://omrbranch.com/api/flights");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(resBodyAsPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object2=jsonObject.get("message");
		String stringMessage = object2.toString();
		System.out.println(stringMessage);
		Object object3 = jsonObject.get("data");
		JSONObject jsonObject2=(JSONObject) object3;
		Object object4=jsonObject2.get("id");
		stringFlightId = object4.toString();
		System.out.println(stringFlightId);
		assertEquals(statusCode, 201,"Status Code");
		assertEquals(stringMessage, "Flight Created Successfully","Flight Created Message");
	}
	
	
	@Test(priority = 2)
	public void login() {
		//1.Header
		addHeader("accept", "application/json");
		//2.Basic Authentication from Authorization in POSTMAN
		addBasicAuth("abdulsensor@ymail.com", "Karim@123");
		//3.Add req type, endpoint
		Response response = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200,"Status Code");
		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
		System.out.println(resBodyAsPrettyString);
	}
	
	
	
}
