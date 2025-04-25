package com.omrbranch;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class C {
	public static void main(String[] args) throws IOException, ParseException {
		
	
	FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\ListFlight.json");
	
	JSONParser jsonParser = new JSONParser();
	
	Object object = jsonParser.parse(fileReader);
	
	JSONObject jsonObject = (JSONObject)object;
	
	Object object2 = jsonObject.get("data");
	
	JSONArray jsonArray = (JSONArray)object2;
	Object object3 = jsonArray.get(2);
	JSONObject jsonObject2=(JSONObject)object3;
	Object object4 = jsonObject2.get("Country");
	System.out.println(object4);
	
	}
}
