package com.omrbranch.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteFlight {

	public static void main(String[] args) {
		RequestSpecification reqSpec;
		reqSpec = RestAssured.given();
		
		reqSpec = reqSpec.header("Content-Type","application/json");
		
//		reqSpec = reqSpec.body("{\r\n"
//				+ "    \"Destinations\": 95\r\n"
//				+ "}");
		
		
		Response response = reqSpec.delete("https://omrbranch.com/api/flight/43113");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		String asString = response.asString();
		System.out.println(asString);
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);

}
}
