package com.bjsxt.biz;

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

public class ScheduleBiz {
	private Scanner input= new Scanner(System.in);
	private ScheduleDao sd= new ScheduleDaoImpl();
	private TeamDao td= new TeamDaoImpl();
	public void mainMenu(){
		while(true){
			System.out.println("-----------------------------------");
			System.out.println("\t\t2018中超联赛管理系统");
			System.out.println("\t\t1.赛程查询");
			System.out.println("\t\t2.赛程添加");
			System.out.println("\t\t3.赛队添加");
			System.out.println("\t\t0.退出");
			System.out.println("-----------------------------------");
			int choice=input.nextInt();
			switch (choice) {
			case 1:
				getByHostName();//根据主队名称查询
				continue;
			case 2:
				addSchedule();//赛程添加
				continue;
			case 3:
				addTeam();
				continue;
			case 0:
				System.out.println("程序退出，谢谢您的使用！");
				break;
			default:
				System.out.println("对不起，输入有误，请重新重入！");
				continue;
			}
			break;
		}
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
		// TODO Auto-generated method stub
		
	}
	private void addSchedule() {
		System.out.println("请输入赛程日期:");
		String date=input.next();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d=sdf.parse(date);
			System.out.println("请输入赛程时间:");
			String time=input.next();
			System.out.println("请选择主队编号:");
			List<Team> list=td.getAllTeam();
			for (Team team : list) {
				System.out.print(team.getTeamId()+"."+team.getTeamName()+" ");
			}
			System.out.println();
			int homeid=input.nextInt();
			while(homeid<=0||homeid>list.size()){
				System.out.println("主队编号选错，请重新选择");
				homeid=input.nextInt();
			}
			System.out.println("请选择客队编号:");
			for (Team team : list) {
				System.out.print(team.getTeamId()+"."+team.getTeamName()+" ");
			}
			int awayid=input.nextInt();
			while(awayid<=0||awayid>list.size()){
				System.out.println("客户编号选错，请重新选择");
				awayid=input.nextInt();
			}
			while(homeid==awayid){
				System.out.println("主队与客队不能为同一个队，请重新选择客队!");
				awayid=input.nextInt();
			}
			System.out.println("请输入比赛结果:");
			String result=input.next();
			System.out.println("请输入比赛场地:");
			String address=input.next();
			System.out.println("请输入比赛场次:");
			int count=input.nextInt();
			//封装成对象
			Schedule sch=new Schedule(new java.sql.Date(d.getTime()), time, homeid, awayid, result, address, count);
			//调用实现类中添加的方法
			int r=sd.addSchedule(sch);
			System.out.println(r>0?"添加成功":"添加失败");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	private void getByHostName() {
		System.out.println("请输入主队名称:");
		String homeName=input.next();
		List<ScheduleEx> list=sd.getByHost(homeName);
		//遍历集合
		for (ScheduleEx s : list) {
			System.out.println(s.getScheduleId()+"\t"+s.getHomeTeamName()+"\t"+s.getAwayTeamName()+"\t"+
		    s.getMatchDate()+"\t"+s.getMatchTime()+"\t"+s.getResult()+"\t"+s.getAddress()+"\t"+s.getCount());
		}
		
	}

}
