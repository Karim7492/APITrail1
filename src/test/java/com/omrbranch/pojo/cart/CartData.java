package com.omrbranch.pojo.cart;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartData {

	 	private int id;
	    private String product_code;
	    private String image;
	    private String name;
	    private String description;
	    private String manage_stock;
	    private String quick_grab;
	    private String is_exclusive;
	    private String status;
	    private int cart_count;
	    private String price;
	    private String special_price;
	    private int discount;
	    private ArrayList<Integer> product_variation_option;
	    private int product_variation_id;
	    private ArrayList<ProductsVariation> variations;
	    private int is_favorite;
}
