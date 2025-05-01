package com.omrbranch.pojo.changeprofilepic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProfilePicture_Output_Pojo {

	private int status;
    private String message;
    private ProfilePicData data;
    private int cart_count;
}
