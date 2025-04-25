package com.omrbranch;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omrbranch.pojo.Student;

public class ListFlightWriteValue {
	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Hello.json");
		ObjectMapper objectMapper= new ObjectMapper();
//		Student stud = new Student("Kar","Kar@gmail.com",1234567891,"CTS");
//		objectMapper.writeValue(file, stud);
//		System.out.println("done");
		
		Address address= new Address("AK","Chennai","JAVA");
		Student stud = new Student("Kar","Kar@gmail.com",1234567891,"CTS", address);
		objectMapper.writeValue(file, stud);
		System.out.println("done");
	}

}
