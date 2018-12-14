package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Team;

public interface TeamDao {
	public List<Team> getAllTeam();
	public int addTeam(Team team);
}
