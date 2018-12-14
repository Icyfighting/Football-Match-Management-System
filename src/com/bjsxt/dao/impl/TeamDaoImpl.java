package com.bjsxt.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bjsxt.dao.BaseDao;
import com.bjsxt.dao.TeamDao;
import com.bjsxt.entity.Team;

public class TeamDaoImpl implements TeamDao {

	@Override
	public List<Team> getAllTeam() {
		//直接调用BaseDao中方法
		return BaseDao.getAllTeam();
		
	}
	
	public static void main(String[] args) {
		TeamDaoImpl teamDaoImpl= new TeamDaoImpl();
		//System.out.println(teamDaoImpl.getAllTeam());
		Team team=new Team("上海申花");
		System.out.println(teamDaoImpl.addTeam(team));
	}

	@Override
	public int addTeam(Team team) {
		//增加队伍，也是调用BaseDao的update方法
		String sql="insert into team values(?,?)";
		List<Integer> list= new ArrayList<>();
		list=BaseDao.getIndex("team");
		Object[] obj={list.get(list.size()-1)+1,team.getTeamName()};
		return BaseDao.update(sql, obj);

	}

}
