package com.omrbranch.pojo.fav;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetUserFavourites_Output_Found_Pojo {
	
	    private int status;
	    private String message;
	    private ArrayList<DataGetUserFavourite> data;
	    private int cart_count;
	    private String currency;

}
