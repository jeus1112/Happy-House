package com.ssafy.happyhouse.dto;

public class InterestAreaDto {

	private int no;
	private String userid;
	private int house_no;
	private String interest_dong;
	private String interest_place;
	private String dealAmount;
	private double latitude;
	private double longitude;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getHouse_no() {
		return house_no;
	}

	public void setHouse_no(int house_no) {
		this.house_no = house_no;
	}

	public String getInterest_dong() {
		return interest_dong;
	}

	public void setInterest_dong(String interest_dong) {
		this.interest_dong = interest_dong;
	}

	public String getInterest_place() {
		return interest_place;
	}

	public void setInterest_place(String interest_place) {
		this.interest_place = interest_place;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}