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
			System.out.println("\t\t2018�г���������ϵͳ");
			System.out.println("\t\t1.���̲�ѯ");
			System.out.println("\t\t2.�������");
			System.out.println("\t\t3.�������");
			System.out.println("\t\t0.�˳�");
			System.out.println("-----------------------------------");
			int choice=input.nextInt();
			switch (choice) {
			case 1:
				getByHostName();//�����������Ʋ�ѯ
				continue;
			case 2:
				addSchedule();//�������
				continue;
			case 3:
				addTeam();
				continue;
			case 0:
				System.out.println("�����˳���лл����ʹ�ã�");
				break;
			default:
				System.out.println("�Բ��������������������룡");
				continue;
			}
			break;
		}
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
		// TODO Auto-generated method stub
		
	}
	private void addSchedule() {
		System.out.println("��������������:");
		String date=input.next();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d=sdf.parse(date);
			System.out.println("����������ʱ��:");
			String time=input.next();
			System.out.println("��ѡ�����ӱ��:");
			List<Team> list=td.getAllTeam();
			for (Team team : list) {
				System.out.print(team.getTeamId()+"."+team.getTeamName()+" ");
			}
			System.out.println();
			int homeid=input.nextInt();
			while(homeid<=0||homeid>list.size()){
				System.out.println("���ӱ��ѡ��������ѡ��");
				homeid=input.nextInt();
			}
			System.out.println("��ѡ��Ͷӱ��:");
			for (Team team : list) {
				System.out.print(team.getTeamId()+"."+team.getTeamName()+" ");
			}
			int awayid=input.nextInt();
			while(awayid<=0||awayid>list.size()){
				System.out.println("�ͻ����ѡ��������ѡ��");
				awayid=input.nextInt();
			}
			while(homeid==awayid){
				System.out.println("������ͶӲ���Ϊͬһ���ӣ�������ѡ��Ͷ�!");
				awayid=input.nextInt();
			}
			System.out.println("������������:");
			String result=input.next();
			System.out.println("�������������:");
			String address=input.next();
			System.out.println("�������������:");
			int count=input.nextInt();
			//��װ�ɶ���
			Schedule sch=new Schedule(new java.sql.Date(d.getTime()), time, homeid, awayid, result, address, count);
			//����ʵ��������ӵķ���
			int r=sd.addSchedule(sch);
			System.out.println(r>0?"��ӳɹ�":"���ʧ��");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	private void getByHostName() {
		System.out.println("��������������:");
		String homeName=input.next();
		List<ScheduleEx> list=sd.getByHost(homeName);
		//��������
		for (ScheduleEx s : list) {
			System.out.println(s.getScheduleId()+"\t"+s.getHomeTeamName()+"\t"+s.getAwayTeamName()+"\t"+
		    s.getMatchDate()+"\t"+s.getMatchTime()+"\t"+s.getResult()+"\t"+s.getAddress()+"\t"+s.getCount());
		}
		
	}

}
