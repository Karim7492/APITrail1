package com.omrbranchwrite;

import java.util.ArrayList;

import com.omrbranch.pojo.Datum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
	
	private int page;
 	private int per_page;
 	private int total;
 	private int total_pages;
 	private ArrayList<Datum> data;
 	private Support support;

}
