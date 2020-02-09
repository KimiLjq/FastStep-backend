package com.stu.fastStep.domain;

import java.sql.Date;

public class Coupon {
	private int id;
	private String name;
	private Date startTime;
	private Date endTime;
	private String image;
	private String url;
	private double beforeMoney;
	private double afterMoney;
	private int saleNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getBeforeMoney() {
		return beforeMoney;
	}
	public void setBeforeMoney(double beforeMoney) {
		this.beforeMoney = beforeMoney;
	}
	public double getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(double afterMoney) {
		this.afterMoney = afterMoney;
	}
	public int getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}
	
	
}
