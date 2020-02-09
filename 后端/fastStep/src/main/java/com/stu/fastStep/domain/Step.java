package com.stu.fastStep.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/*
 * ×ã¼£µÄpojo
 */
public class Step {
	private int id;
	private int userId;
	private int couponId;
	private Timestamp time=null;
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	
	
}
