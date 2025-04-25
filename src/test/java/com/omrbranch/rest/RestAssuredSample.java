package com.omrbranch.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredSample {
	
	public static void main(String[] args) {
		
		
				RequestSpecification reqSpec;
				// 1. Initialize the RestAssured Class
				reqSpec = RestAssured.given();

				// Header, Req body, Auth

				// 2. Header
				reqSpec = reqSpec.header("Content-Type", "application/json");

				// 3. Req Body/ Payload
				reqSpec = reqSpec.body("{\r\n" + "    \"flightName\": \"AirIndia\",\r\n" + "    \"Country\": \"India\",\r\n"
						+ "    \"Destinations\": \"87\",\r\n"
						+ "    \"URL\": \"https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India\"\r\n" + "}");

				// 4. Pass the endpoint
				Response response = reqSpec.post("https://omrbranch.com/api/flights");

				// Status Code
				int statusCode = response.getStatusCode();
				System.out.println(statusCode);

				// Get the res body asString
				String asString = response.asString();
				System.out.println(asString);

				// Get the res body asPrettyString
				String asPrettyString = response.asPrettyString();
				System.out.println(asPrettyString);
			}
		

	}


