package com.bjsxt.entity;

import java.util.Date;

public class ScheduleEx extends Schedule{
	private String homeTeamName;
	private String awayTeamName;
	public String getHomeTeamName() {
		return homeTeamName;
	}
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public ScheduleEx(Date matchDate, String matchTime, int homeTeamId, int awayTeamId, String result, String address,
			int count) {
		super(matchDate, matchTime, homeTeamId, awayTeamId, result, address, count);
	}
	public ScheduleEx(int scheduleId, Date matchDate, String matchTime, String result, String address, int count,
			String homeTeamName, String awayTeamName) {
		super(scheduleId, matchDate, matchTime, result, address, count);
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
	}
	public ScheduleEx() {
		super();
	}
	@Override
	public String toString() {
		return "ScheduleEx [" +getScheduleId()+"\t"+getMatchDate()+"\t"+getMatchTime()+"\t" +homeTeamName +"\t"+ awayTeamName +"\t"+getResult()+"\t"+getAddress()+"\t"+getCount()+ "]";
	}
	
	

}
