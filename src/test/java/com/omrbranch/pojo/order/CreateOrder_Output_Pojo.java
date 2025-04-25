package com.omrbranch.pojo.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrder_Output_Pojo {
	
	private int status;
    private String message;
    private String currency;
    private OrderData data;
    private int order_id;

}
