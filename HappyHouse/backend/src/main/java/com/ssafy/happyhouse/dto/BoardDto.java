package com.ssafy.happyhouse.dto;

public class BoardDto {

	private int no;
	private String userid;
	private String title;
	private String content;
	private String regtime;

	public BoardDto() {
		super();
	}

	public BoardDto(int no, String userid, String title, String content, String regtime) {
		super();
		this.no = no;
		this.userid = userid;
		this.title = title;
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
