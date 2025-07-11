package com.omrbranch.pojo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderData {

	private int id;
    private String order_no;
    private String amount;
    private boolean wallet_show;
    private String wallet_amount;
    private String remaining_amount;
    private String duration;
}
