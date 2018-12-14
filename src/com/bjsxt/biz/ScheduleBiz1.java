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
	
	//���˵�����
	//ҵ���߼���ͱ�ʾ��ĺ��壬��Ҫ����������ݴ����ĸ���������
	private Scanner input= new Scanner(System.in);
	private ScheduleDao sd= new ScheduleDaoImpl();
	private TeamDao td= new TeamDaoImpl();
	public void mainMenu(){
		while(true){
			System.out.println("=============��ӭʹ���г�����ϵͳ============");
			System.out.println("=============1.��ѯ����==================");
			System.out.println("=============2.�������==================");
			System.out.println("=============3.�������==================");
			System.out.println("=============4.�˳�ϵͳ==================");
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
				System.out.println("��лʹ�ã���ӭ����");
				System.exit(0);
				break;
			default:
				continue;
			}
		}
	}
	private void addSchedule() {
		System.out.println("���������ӱ��");
		List<Team> list= td.getAllTeam();
		for (Team team : list) {
			System.out.print(team.getTeamId()+"."+team.getTeamName());
		}
		System.out.println();
		int hostId=input.nextInt();
		while(hostId<=0||hostId>list.size()){
			System.out.print("�������ӱ�Ŵ�������������:");
			hostId=input.nextInt();
		}
		System.out.println("������Ͷӱ��");
		for (Team team : list) {
			System.out.print(team.getTeamId()+"."+team.getTeamName());
		}
		System.out.println();
		int awayId=input.nextInt();
		while(awayId<=0||awayId>list.size()||hostId==awayId){
			System.out.print("����Ͷӱ�Ŵ�����������ظ�������������:");
			awayId=input.nextInt();
		}

		System.out.println("������������ڣ�");
		String matchDate=input.next();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
		Date d=new Date();
		try {
			d=sdf.parse(matchDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���ԣ���������Ϊ��"+d);
		
		System.out.println("���������ʱ�䣺");
		String matchTime = input.next();
		System.out.println("��������������");
		String result= input.next();
		System.out.println("������������أ�");
		String address = input.next();
		System.out.println("������������Σ�");
		int count = input.nextInt();
		
		Schedule schedule= new Schedule(new java.sql.Date(d.getTime()), matchTime, hostId, awayId, result, address, count);//ע��date���ͣ�����û̫��������Ҫ����sql.date
		System.out.println(sd.addSchedule(schedule)>0?"��ӳɹ�":"���ʧ��");

	}
	private void addTeam() {
		System.out.println("���������Ӷ�������:");
		String newTeamName=input.next();
		//��Ҫ�ж���ӵ�û�д��ڣ�������ӽ�ȥ����Ҫ�������ж������ƣ��������������
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
			System.out.println("��������Ѿ����ڣ��޷��ظ����");
			return;
		}else{
			Team newTeam= new Team(newTeamName);
			System.out.println(td.addTeam(newTeam)>0?"��ӳɹ�":"���ʧ��");
		}
		
	}
	private void getByHost() {
		System.out.println("��������������");
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
