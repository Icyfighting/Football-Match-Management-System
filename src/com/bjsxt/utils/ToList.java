package com.bjsxt.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ToList {
	public static <E> List<E> resultSet2List(ResultSet rSet, Class<E> clazz) {// ��һ��E������д��������ʶE��ռλ��
		// ��Ҫ��List
		List<E> list = new ArrayList<>();

		// ResultSet��ÿ��ȡ��������ӦList��һ��Ԫ�أ�Ԫ����E���ͣ���Ҫͨ��clazz��ȡE���͵ĸ��ֶΣ���ӦResultSet�ĸ����У�clazz�޲��������һ��E����ȫ��ͨ��set�������и�ֵ
		// rSet�е�ÿ��ÿ��E��ÿ���У�����һ����Ҫset��ֵ��ÿ�����һ��E���ͼ��뵽List��
		// ��ȡÿ��rSetÿ���е���������Ϊ�����rSet���еģ����Բ��ط����ڲ�ѭ���С����������У���Ϊʵ��������Ժͱ��������Ӧ���Ǿͻ�ȡʵ�����ֶκ���
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		try {
			while (rSet.next()) {
				// ȡ��ÿһ�У�
				// ����һ��E����bean�����ڽ���ÿ�ж�Ӧ�Ķ���
				E bean = clazz.getConstructor().newInstance();
				for (int i = 0; i < fields.length; i++) {
					String fName = fields[i].getName();
					String methodName = "set" + fName.toUpperCase().substring(0, 1) + fName.substring(1);
					System.out.println("methodName:"+methodName);
					//System.out.println("field.getClass:"+fields[i].getClass());
					//System.out.println("field.getType"+fields[i].getType());
					Method method = clazz.getDeclaredMethod(methodName, fields[i].getType());
					Object value = rSet.getObject(fName);
					//System.out.println("value:"+value);
					method.invoke(bean, value);
				}
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
