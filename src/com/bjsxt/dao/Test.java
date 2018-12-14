package com.bjsxt.dao;

import java.util.Date;

import com.bjsxt.entity.Schedule;

public class Test {
	public static void main(String[] args) {
		insert();
	}

	public static void update() {
		// 改
		String sql = "update schedule set result=? where scheduleId=?";
		System.out.println(BaseDao.update(sql, "0:0", 5) > 0 ? "OK" : "NOK");
	}

	public static void delete() {
		// 删除
		String sql = "delete from schedule where scheduleId=?";
		System.out.println(BaseDao.update(sql, 7) > 0 ? "OK" : "NOK");
	}

	public static void insert() {
		// 增加
		String sql = "insert into schedule values(seq_scheduleId.nextval,?,?,?,?,?,?,?)";
		Schedule s = new Schedule(new java.sql.Date(new Date().getTime()), "15:00", 1, 2, "3:2", "北京工体", 30);
		Object[] obj = { s.getMatchDate(), s.getMatchTime(), s.getHomeTeamId(), s.getAwayTeamId(), s.getResult(),
				s.getAddress(), s.getCount() };
		System.out.println(BaseDao.update(sql, obj) > 0 ? "OK" : "NOK");
	}
}
