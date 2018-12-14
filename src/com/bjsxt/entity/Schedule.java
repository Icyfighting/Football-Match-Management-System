package com.bjsxt.entity;

import java.util.Date;

public class Schedule {
	
	protected int scheduleId;
	protected Date matchDate;
	protected String matchTime;
	protected int homeTeamId;
	protected int awayTeamId;
	protected String result;
	protected String address;
	protected int count;
	
	

	
	
	public Schedule(Date matchDate, String matchTime, int homeTeamId, int awayTeamId, String result, String address,
			int count) {
		super();
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.homeTeamId = homeTeamId;
		this.awayTeamId = awayTeamId;
		this.result = result;
		this.address = address;
		this.count = count;
	}


	public Schedule(int scheduleId, Date matchDate, String matchTime, String result, String address, int count) {
		super();
		this.scheduleId = scheduleId;
		this.matchDate = matchDate;
		this.matchTime = matchTime;
		this.result = result;
		this.address = address;
		this.count = count;
	}


	public Schedule() {
		super();
	}


	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	public String getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}
	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}
	public int getAwayTeamId() {
		return awayTeamId;
	}
	public void setAwayTeamId(int awayTeamId) {
		this.awayTeamId = awayTeamId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", matchDate=" + matchDate + ", matchTime=" + matchTime
				+ ", homeTeamId=" + homeTeamId + ", awayTeamId=" + awayTeamId + ", result=" + result + ", address="
				+ address + ", count=" + count + "]";
	}


}
