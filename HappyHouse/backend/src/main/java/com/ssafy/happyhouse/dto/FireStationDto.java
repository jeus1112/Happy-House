package com.ssafy.happyhouse.dto;

public class FireStationDto {

	private int no;
	private String station_name;
	private String address;
	private String gu;
	private double latitude;
	private double longitude;

	public FireStationDto() {
		super();
	}

	public FireStationDto(int no, String station_name, String address, String gu, double latitude, double longitude) {
		super();
		this.no = no;
		this.station_name = station_name;
		this.address = address;
		this.gu = gu;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
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