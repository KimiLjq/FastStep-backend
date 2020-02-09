package com.stu.fastStep.domain;

import java.sql.Timestamp;

public class Seckill {
	private int id;
	private String itemName;
	private double oriPrice;
	private double prePrice;
	private String itemLink;
	private String picLink;
	private Timestamp startTime;
	private Timestamp endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getOriPrice() {
		return oriPrice;
	}
	public void setOriPrice(double oriPrice) {
		this.oriPrice = oriPrice;
	}
	public double getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(double prePrice) {
		this.prePrice = prePrice;
	}
	public String getItemLink() {
		return itemLink;
	}
	public void setItemLink(String itemLink) {
		this.itemLink = itemLink;
	}
	public String getPicLink() {
		return picLink;
	}
	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
