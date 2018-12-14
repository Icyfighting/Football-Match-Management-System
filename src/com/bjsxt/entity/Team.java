package com.bjsxt.entity;

public class Team {
	private int teamId;
	private String teamName;
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Team(int teamId, String teamName) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
	}
	public Team() {
		super();
	}
	@Override
	public String toString() {
		return "Team [" + teamId + "," + teamName + "]";
	}
	public Team(String teamName) {
		super();
		this.teamName = teamName;
	}

	
}
