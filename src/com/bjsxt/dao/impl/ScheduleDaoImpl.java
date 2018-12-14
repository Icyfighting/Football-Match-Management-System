package com.bjsxt.dao.impl;

import java.util.Date;
import java.util.List;

import com.bjsxt.dao.BaseDao;
import com.bjsxt.dao.ScheduleDao;
import com.bjsxt.entity.Schedule;
import com.bjsxt.entity.ScheduleEx;


public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public List<ScheduleEx> getByHost(String hostName) {
		String sql="select scheduleid, matchdate,matchtime,h.teamname as hometeamname, a.teamname as awayteamname,result,address,count from schedule s, team h, team a where s.hometeamid=h.teamid and s.awayteamid=a.teamid and h.teamname like '%"+hostName+"%'";
		return BaseDao.query(sql);
	}

	@Override
	public int addSchedule(Schedule s) {
		// 调用BaseDao中的update方法
		String sql="insert into schedule values(?,?,?,?,?,?,?,?)";
		List<Integer> list=BaseDao.getIndex("schedule");
		int index =list.get(list.size()-1)+1;
		Object[] obj={index,s.getMatchDate(),s.getMatchTime(),s.getHomeTeamId(),
				s.getAwayTeamId(),s.getResult(),s.getAddress(),s.getCount()	
		};
		return BaseDao.update(sql, obj);

	}
	
	public static void main(String[] args) {
		ScheduleDaoImpl sdi= new ScheduleDaoImpl();
		Schedule s= new Schedule(new Date(), "15:00", 1, 2, "2:3", "工体", 30);
		System.out.println(sdi.addSchedule(s));
	}

}
