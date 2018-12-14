package com.bjsxt.biz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.bjsxt.dao.ScheduleDao;
import com.bjsxt.dao.TeamDao;
import com.bjsxt.dao.impl.ScheduleDaoImpl;
import com.bjsxt.dao.impl.TeamDaoImpl;
import com.bjsxt.entity.Schedule;
import com.bjsxt.entity.ScheduleEx;
import com.bjsxt.entity.Team;

public class ScheduleBiz1 {
	
	//主菜单方法
	//业务逻辑层和表示层的合体，主要负责调用数据处理层的各个方法，
	private Scanner input= new Scanner(System.in);
	private ScheduleDao sd= new ScheduleDaoImpl();
	private TeamDao td= new TeamDaoImpl();
	public void mainMenu(){
		while(true){
			System.out.println("=============欢迎使用中超赛程系统============");
			System.out.println("=============1.查询赛程==================");
			System.out.println("=============2.添加赛程==================");
			System.out.println("=============3.添加赛队==================");
			System.out.println("=============4.退出系统==================");
			System.out.println("=======================================");
			int choice=input.nextInt();
			switch (choice) {
			case 1:
				getByHost();
				continue;
			case 2:
				addSchedule();
				continue;
			case 3:
				addTeam();
				continue;
			case 4:
				System.out.println("感谢使用，欢迎再来");
				System.exit(0);
				break;
			default:
				continue;
			}
		}
	}
	private void addSchedule() {
		System.out.println("请输入主队编号");
		List<Team> list= td.getAllTeam();
		for (Team team : list) {
			System.out.print(team.getTeamId()+"."+team.getTeamName());
		}
		System.out.println();
		int hostId=input.nextInt();
		while(hostId<=0||hostId>list.size()){
			System.out.print("输入主队编号错误，请重新输入:");
			hostId=input.nextInt();
		}
		System.out.println("请输入客队编号");
		for (Team team : list) {
			System.out.print(team.getTeamId()+"."+team.getTeamName());
		}
		System.out.println();
		int awayId=input.nextInt();
		while(awayId<=0||awayId>list.size()||hostId==awayId){
			System.out.print("输入客队编号错误或与主队重复，请重新输入:");
			awayId=input.nextInt();
		}

		System.out.println("请输入比赛日期：");
		String matchDate=input.next();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
		Date d=new Date();
		try {
			d=sdf.parse(matchDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("测试：输入日期为："+d);
		
		System.out.println("请输入比赛时间：");
		String matchTime = input.next();
		System.out.println("请输入比赛结果：");
		String result= input.next();
		System.out.println("请输入比赛场地：");
		String address = input.next();
		System.out.println("请输入比赛场次：");
		int count = input.nextInt();
		
		Schedule schedule= new Schedule(new java.sql.Date(d.getTime()), matchTime, hostId, awayId, result, address, count);//注意date类型，还是没太懂哪里需要定义sql.date
		System.out.println(sd.addSchedule(schedule)>0?"添加成功":"添加失败");

	}
	private void addTeam() {
		System.out.println("请输入增加队伍名称:");
		String newTeamName=input.next();
		//需要判断添加的没有存在，才能添加进去。需要遍历所有队伍名称，不包含才能添加
		List<Team> list = new ArrayList<>();
		list =td.getAllTeam();
		boolean flag= false;
		for (Team team : list) {
			if(team.getTeamName().equals(newTeamName)){
				flag=true;
				break;
			}
		}
		if(flag){
			System.out.println("输入队伍已经存在，无法重复添加");
			return;
		}else{
			Team newTeam= new Team(newTeamName);
			System.out.println(td.addTeam(newTeam)>0?"添加成功":"添加失败");
		}
		
	}
	private void getByHost() {
		System.out.println("请输入主队名称");
		String hostName=input.next();
		List<ScheduleEx> list =sd.getByHost(hostName);
		for (ScheduleEx s : list) {
			System.out.println(s);
		}
		
	}
	
	public static void main(String[] args) {
		ScheduleBiz1 sb1= new ScheduleBiz1();
		sb1.mainMenu();
	}

}
