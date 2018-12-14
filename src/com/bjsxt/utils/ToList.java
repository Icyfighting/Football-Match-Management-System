package com.bjsxt.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ToList {
	public static <E> List<E> resultSet2List(ResultSet rSet, Class<E> clazz) {// 第一个E还必须写，否则不认识E是占位符
		// 先要有List
		List<E> list = new ArrayList<>();

		// ResultSet中每行取出来，对应List中一个元素，元素是E类型，需要通过clazz获取E类型的个字段，对应ResultSet的各个列，clazz无参数构造出一个E对象，全部通过set方法进行赋值
		// rSet中的每个每个E，每个列，都是一个需要set的值。每构造出一个E，就加入到List中
		// 获取每个rSet每个列的列名，因为这个是rSet共有的，所以不必放在内层循环中。靠，还不行，因为实体类的属性和表的列明对应，那就获取实体类字段好了
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		try {
			while (rSet.next()) {
				// 取出每一行，
				// 创建一个E对象bean，用于接收每行对应的对象
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
