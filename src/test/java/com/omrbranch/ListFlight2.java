package com.omrbranch;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ListFlight2 {
	public static void main(String[] args) throws IOException, ParseException {
		FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\ListFlight2.json");
		JSONParser jsonParser = new JSONParser();
		Object object = jsonParser.parse(fileReader);
		JSONObject jsonObject = (JSONObject) object;
		Object object2 = jsonObject.get("page");
		System.out.println(object2);
		Object object3 = jsonObject.get("per_page");
		System.out.println(object3);
		Object object4 = jsonObject.get("total");
		System.out.println(object4);
		Object object5 = jsonObject.get("total_pages");
		System.out.println(object5);
		Object object6 = jsonObject.get("data");
		JSONArray jsonArray=(JSONArray) object6;
		for (int i = 0; i < jsonArray.size(); i++) {
			System.out.println("----Flight "+(i+1)+" ----");
			JSONArray jsonArray1=(JSONArray) object6;
			Object object8 = jsonArray1.get(i);
			JSONObject flight=(JSONObject) object8;
			Object id = flight.get("id");
			System.out.println(id);
			Object flightName = flight.get("flightName");
			System.out.println(flightName);
			Object Country = flight.get("Country");
			System.out.println(Country);
			Object Destinations = flight.get("Destinations");
			System.out.println(Destinations);
			Object URL = flight.get("URL");
			System.out.println(URL);
		}
		Object object7 = jsonObject.get("support");
		System.out.println("---Support---");
		JSONObject jsonObject2= (JSONObject)object7;
		Object object8 = jsonObject2.get("url");
		System.out.println(object8);
		Object object9 = jsonObject2.get("text");
		System.out.println(object9);
	}
}
