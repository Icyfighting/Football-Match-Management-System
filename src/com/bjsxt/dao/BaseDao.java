package com.bjsxt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bjsxt.entity.ScheduleEx;
import com.bjsxt.entity.Team;
//import com.bjsxt.utils.ToList;

public class BaseDao {
	// ��̬�ֶ�
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PWD;
	// ��̬������ʼ����
	static {
		// �����ļ�
		File file = new File("match.properties");
		// �������Զ���
		Properties properties = new Properties();
		// ����ļ�������
		if (!file.exists()) {
			DRIVER = "oracle.jdbc.driver.OracleDriver";
			URL = "jdbc:oracle:thin:@192.168.3.35:1521:ORCL";
			USER = "match";
			PWD = "123";
			// ��ֵ�����Զ���
			properties.setProperty("orcalDriver", DRIVER);
			properties.setProperty("orcalUrl", URL);
			properties.setProperty("orcalUser", USER);
			properties.setProperty("orcalPwd", PWD);
			properties.setProperty("TYPE", "oracle");

			// ���Զ���洢�������ļ���
			try {
				properties.store(new FileOutputStream(file), "���Գ�ʼ");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// ������ζ�Ҫ�������ļ��ж�ȡ
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = properties.getProperty("TYPE");
		if ("oracle".equals(type)) {
			DRIVER = properties.getProperty("orcalDriver");
			URL = properties.getProperty("orcalUrl");
			USER = properties.getProperty("orcalUser");
			PWD = properties.getProperty("orcalPwd");
		} else {
			DRIVER = properties.getProperty("mysqlDriver");
			URL = properties.getProperty("mysqlUrl");
			USER = properties.getProperty("mysqlUser");
			PWD = properties.getProperty("mysqlPwd");
		}

	}

	public static void main(String[] args) {
		/*
		 * String sql=
		 * " select scheduleid, matchdate,matchtime,h.teamname, a.teamname,result,address,count from schedule s, team h, team a where s.hometeamid=h.teamid and s.awayteamid=a.teamid and h.teamname like '%����%'"
		 * ; System.out.println(query(sql));
		 */
		// System.out.println(getConn());
		System.out.println(getIndex("team"));
	}

	// update����������ɾ�ģ�ӦΪ����������ͬ������Ĳ����Ƿ����⣬��Ĳ����ò���������������Ĳ�����ȷ�����ÿɱ��������������Ҳ��ȷ������object
	public static int update(String sql, Object... obj) {
		// ��ȡ����
		Connection connection = getConn();
		PreparedStatement statement = null;
		int result = 0;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				statement.setObject(i + 1, obj[i]);
				;
			}
			result = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(null, statement, connection);
		}
		return result;
	}

	// query���������ѯ����Ҫ�Ǹ����������Ʋ�ѯ��
	public static List<ScheduleEx> query(String sql, Object... obj) {
		ResultSet rSet = null;
		//List<ScheduleEx> list = new ArrayList<>();
		Connection connection = getConn();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				statement.setObject(i + 1, obj[i]);
				;
			}
			rSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { while (rSet.next()) { int scheduleId = rSet.getInt(1); Date
		 * matchDate = rSet.getDate(2); String matchTime = rSet.getString(3);
		 * String homeTeamName = rSet.getString(4); String awayTeamName =
		 * rSet.getString(5); String result = rSet.getString(6); String address
		 * = rSet.getString(7); int count = rSet.getInt(8); ScheduleEx se = new
		 * ScheduleEx(scheduleId, matchDate, matchTime, result, address, count,
		 * homeTeamName, awayTeamName); list.add(se); } } catch (SQLException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } finally {
		 * closeAll(rSet, statement, connection); }
		 */
		return BaseDao.resultSet2List(rSet, ScheduleEx.class);

	}

	public static List<Team> getAllTeam() {
		//List<Team> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "select * from team";
		connection = getConn();

		try {
			statement = connection.prepareStatement(sql);
			rSet = statement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	/*	try {
			while (rSet.next()) {
				int teamId = rSet.getInt(1);
				String teamName = rSet.getString(2);
				Team team = new Team(teamId, teamName);
				list.add(team);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return BaseDao.resultSet2List(rSet, Team.class);
	}

	// ���connection
	public static Connection getConn() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	// �ر�ȫ��
	public static void closeAll(ResultSet rSet, Statement stat, Connection conn) {
		if (rSet != null) {
			try {
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ��ȡid�еķ�������Ȼ�Ҿ��û�ȡ���һ��ֵ�Ϳ����ˡ�
	public static List<Integer> getIndex(String table) {
		//List<Integer> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = null;
		if ("schedule".equals(table)) {
			sql = "select scheduleid from schedule order by scheduleid";
		} else {
			sql = "select teamid from team order by teamid";
		}
		connection = getConn();
		try {
			statement = connection.prepareStatement(sql);
			rSet = statement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		return BaseDao.resultSet2List(rSet, Integer.class);
	}
	
	
	public static <T> List<T> resultSet2List(ResultSet resultSet, Class<T> clazz) {  //T����Team
		//����һ��List
		List<T> list = new ArrayList<T>();
		try {
			//��ȡ��ǰ�����е�����
			Field[] dfields = clazz.getDeclaredFields();
			System.out.println("������BaseDao2�ǲ���������");
			//���ȱ���resultSet
			while (resultSet.next()) {
				//����һ������
				T bean = clazz.getConstructor().newInstance();
				//��������
				for (int i = 0; i < dfields.length; i++) {
					//��ȡ���Ե�����
					String fieldName = dfields[i].getName();
					//��ȡ���Զ�Ӧ��ֵ
					Object fieldValue = resultSet.getObject(fieldName);
					//��ȡ���Ե�set����
					String methodName = "set" + fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1);
					//��ȡ����
					Method setMethod = clazz.getMethod(methodName, dfields[i].getType());
					//Ҫ��ֵ���ø�����
					setMethod.invoke(bean, fieldValue);
				}
				//Ȼ�󽫶�����ӵ�List
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
