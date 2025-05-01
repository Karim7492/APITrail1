package com.omrbranch.pojo.fav;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManageUserFavourites_Output_Pojo {
	
	private int status;
    private String message;
    private ArrayList<FavouiteData> data;
    private int cart_count;
    private String currency;

}
