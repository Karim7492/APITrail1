package com.omrbranch.pojo;

import com.omrbranch.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private String name;
	private String mailId;
	private long phoneNo;
	private String companyName;
	private Address address;
	
	
	


//	public Student(String name, String mailId, long phoneNo, String companyName, Address address) {
//		this.name = name;
//		this.mailId = mailId;
//		this.phoneNo = phoneNo;
//		this.companyName = companyName;
//		this.address = address;
//	}
//
//
//
//
//	public Address getAddress() {
//		return address;
//	}
//
//
//
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//
//
//
//
//	
//
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public String getMailId() {
//		return mailId;
//	}
//
//
//	public void setMailId(String mailId) {
//		this.mailId = mailId;
//	}
//
//
//	public long getPhoneNo() {
//		return phoneNo;
//	}
//
//
//	public void setPhoneNo(long phoneNo) {
//		this.phoneNo = phoneNo;
//	}
//
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}

}
