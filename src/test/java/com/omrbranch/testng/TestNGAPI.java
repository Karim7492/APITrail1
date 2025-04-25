package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestNGAPI extends BaseClass {
	RequestSpecification reqSpec;
	private String stringFlightId;
	@BeforeClass
	private void beforeClass() {
		reqSpec = RestAssured.given();
		
		reqSpec = reqSpec.header("Content-Type","application/json");
			
		
	}

	@Test(priority = 1)
	
	private void postFlight() throws ParseException {
		reqSpec = reqSpec.body("{\r\n"
				+ "    \"flightName\": \"Karim\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": \"87\",\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		
		
		Response response = reqSpec.post("https://omrbranch.com/api/flights");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String asString = response.asString();
		System.out.println(asString);
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
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
	private void getFlight() throws ParseException {
			
		
		Response response = reqSpec.get("https://omrbranch.com/api/flight/"+stringFlightId);
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String asString = response.asString();
		System.out.println(asString);
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object3 = jsonObject.get("data");
		JSONObject jsonObject1=(JSONObject) object3;
		Object object4=jsonObject1.get("id");
		String stringFlightIdNew = object4.toString();
		System.out.println(stringFlightIdNew);
		assertEquals(statusCode, 200,"Status Code");
		assertEquals(stringFlightId, stringFlightIdNew,"Verifying Flight ID");
		
	}
	@Test(priority = 3)
	private void putFlight() throws ParseException {
		
		reqSpec = reqSpec.body("{\r\n"
				+ "    \"flightName\": \"AirIndia\",\r\n"
				+ "    \"Country\": \"India\",\r\n"
				+ "    \"Destinations\": 67,\r\n"
				+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n"
				+ "}");
		
		
Response response = reqSpec.put("https://omrbranch.com/api/flight/"+stringFlightId);
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String asString = response.asString();
		System.out.println(asString);
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object2=jsonObject.get("message");
		String stringMessage = object2.toString();
		System.out.println(stringMessage);		
		assertEquals(statusCode, 200,"Status Code");
		assertEquals(stringMessage, "Flight Updated Successfully","Flight updated Message");
		

	}
	
	@Test(priority = 4)
	private void patchFlight() throws ParseException {
		reqSpec = reqSpec.body("{\r\n"
				+ "    \"Destinations\": 97\r\n"
				+ "}");
		Response response = reqSpec.patch("https://omrbranch.com/api/flight/"+stringFlightId);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		String asString = response.asString();
		System.out.println(asString);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object2=jsonObject.get("message");
		String stringMessage = object2.toString();
		System.out.println(stringMessage);		
		assertEquals(statusCode, 200,"Status Code");
		assertEquals(stringMessage, "Flight Updated Successfully","Flight updated Message");
	}
	
	@Test(priority = 5)
	private void deleteFlight() throws ParseException {
		Response response = reqSpec.delete("https://omrbranch.com/api/flight/"+stringFlightId);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		String asString = response.asString();
		System.out.println(asString);
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		assertEquals(statusCode, 204,"Status Code");
	}
	@Test(priority = 6)
	private void listFlight() throws ParseException {
		
		Response response = reqSpec.get("https://omrbranch.com/api/flights?page=1");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String asString = response.asString();
		System.out.println(asString);
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(asPrettyString);
		JSONObject jsonObject = (JSONObject) object;
		Object object3 = jsonObject.get("page");
		String pageNo = object3.toString();
		System.out.println(pageNo);
		assertEquals(statusCode, 200,"Status Code");
		assertEquals(pageNo, "1","Verifying page number");
		
	}
	
}
