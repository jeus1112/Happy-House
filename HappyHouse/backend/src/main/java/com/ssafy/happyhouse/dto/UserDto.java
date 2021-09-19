package com.ssafy.happyhouse.dto;

public class UserDto {

	private String userid;
	private String pass;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String favorite_place_1;
	private String favorite_place_2;

	public UserDto() {
		super();
	}

	public UserDto(String userid, String pass, String name, String phone, String email, String address,
			String favorite_place_1, String favorite_place_2) {
		super();
		this.userid = userid;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.favorite_place_1 = favorite_place_1;
		this.favorite_place_2 = favorite_place_2;
	}

	public UserDto(String userid, String pass) {
		super();
		this.userid = userid;
		this.pass = pass;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFavorite_place_1() {
		return favorite_place_1;
	}

	public void setFavorite_place_1(String favorite_place_1) {
		this.favorite_place_1 = favorite_place_1;
	}

	public String getFavorite_place_2() {
		return favorite_place_2;
	}

	public void setFavorite_place_2(String favorite_place_2) {
		this.favorite_place_2 = favorite_place_2;
	}

}
