package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.entity.Schedule;
import com.bjsxt.entity.ScheduleEx;

public interface ScheduleDao {
	//�����������ƣ�ģ����ѯ����Ҫ������̣������֮�䲻�ǿ����󴫵����ǲ���Ӧ��Ū��ֻ��host���ƵĶ���
	public List<ScheduleEx> getByHost(String hostName);
	//��������
	public int addSchedule(Schedule schedule);
	//�������������������̵Ĳ�������ɾ�����̣��޸�������Ϣ

}
