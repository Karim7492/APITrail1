package com.omrbranch.pojo.category;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryList_Output_Pojo {
	
	private int status;
    private String message;
    private String currency;
    private ArrayList<ProductData> data;
    private String banner;
    private int cart_count;

}
