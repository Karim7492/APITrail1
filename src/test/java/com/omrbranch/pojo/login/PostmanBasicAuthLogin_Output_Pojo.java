package com.omrbranch.pojo.login;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostmanBasicAuthLogin_Output_Pojo {
	
	private int status;
    private String message;
    private Login data;
    private String refer_msg;
    private int cart_count;
    private String role;

}
