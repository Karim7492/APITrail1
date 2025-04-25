package com.omrbranchwrite;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class A {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Hello1.json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		BPojo b= new BPojo("A", "b", 1234567891, "a");
		objectMapper.writeValue(file, b);
		
	}
	
}
