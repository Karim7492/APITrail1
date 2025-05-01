package com.omrbranch.pojo.fav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class AttributeOptionGetUserFav {
	
	private int id;
    private int attribute_id;
    private String value;
    private String status;
    private String created_at;
    private String updated_at;

}
