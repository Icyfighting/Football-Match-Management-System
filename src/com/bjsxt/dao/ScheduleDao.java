package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Schedule;
import com.bjsxt.entity.ScheduleEx;

public interface ScheduleDao {
	//根据主队名称，模糊查询符合要求的赛程，各层次之间不是靠对象传递吗？是不是应该弄个只有host名称的对象？
	public List<ScheduleEx> getByHost(String hostName);
	//增加赛程
	public int addSchedule(Schedule schedule);
	//可以增加其他对于赛程的操作，如删除赛程，修改赛程信息

}
