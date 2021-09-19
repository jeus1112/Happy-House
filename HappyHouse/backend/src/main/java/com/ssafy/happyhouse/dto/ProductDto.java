package com.ssafy.happyhouse.dto;

public class ProductDto {
	private int no;
	private String userid;
	private String title;
	private String address;
	private String price;
	private String sale_type;
	private String content;
	private String regtime;

	public ProductDto() {
		super();
	}

	public ProductDto(int no, String title, String userid, String address, String price, String sale_type,
			String content, String regtime) {
		super();
		this.no = no;
		this.title = title;
		this.userid = userid;
		this.address = address;
		this.price = price;
		this.sale_type = sale_type;
		this.content = content;
		this.regtime = regtime;

	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSale_type() {
		return sale_type;
	}

	public void setSale_type(String sale_type) {
		this.sale_type = sale_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

}
