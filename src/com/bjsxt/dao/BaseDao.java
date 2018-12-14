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
	// 静态字段
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PWD;
	// 静态代码块初始属性
	static {
		// 创建文件
		File file = new File("match.properties");
		// 创建属性对象
		Properties properties = new Properties();
		// 如果文件不存在
		if (!file.exists()) {
			DRIVER = "oracle.jdbc.driver.OracleDriver";
			URL = "jdbc:oracle:thin:@192.168.3.35:1521:ORCL";
			USER = "match";
			PWD = "123";
			// 赋值给属性对象
			properties.setProperty("orcalDriver", DRIVER);
			properties.setProperty("orcalUrl", URL);
			properties.setProperty("orcalUser", USER);
			properties.setProperty("orcalPwd", PWD);
			properties.setProperty("TYPE", "oracle");

			// 属性对象存储到属性文件中
			try {
				properties.store(new FileOutputStream(file), "属性初始");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 无论如何都要从属性文件中读取
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
		 * " select scheduleid, matchdate,matchtime,h.teamname, a.teamname,result,address,count from schedule s, team h, team a where s.hometeamid=h.teamid and s.awayteamid=a.teamid and h.teamname like '%北京%'"
		 * ; System.out.println(query(sql));
		 */
		// System.out.println(getConn());
		System.out.println(getIndex("team"));
	}

	// update方法负责增删改，应为返回类型相同，不变的部分是方法题，变的部分用参数传，几个命令的参数不确定，用可变参数。传的类型也不确定，用object
	public static int update(String sql, Object... obj) {
		// 获取连接
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

	// query方法负责查询，主要是根据主队名称查询。
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

	// 获得connection
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

	// 关闭全部
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

	// 获取id列的方法，虽然我觉得获取最后一个值就可以了。
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
	
	
	public static <T> List<T> resultSet2List(ResultSet resultSet, Class<T> clazz) {  //T代表Team
		//声明一个List
		List<T> list = new ArrayList<T>();
		try {
			//获取当前类所有的属性
			Field[] dfields = clazz.getDeclaredFields();
			System.out.println("测试下BaseDao2是不是运行了");
			//首先遍历resultSet
			while (resultSet.next()) {
				//创建一个对象
				T bean = clazz.getConstructor().newInstance();
				//遍历属性
				for (int i = 0; i < dfields.length; i++) {
					//获取属性的名字
					String fieldName = dfields[i].getName();
					//获取属性对应的值
					Object fieldValue = resultSet.getObject(fieldName);
					//获取属性的set方法
					String methodName = "set" + fieldName.toUpperCase().substring(0, 1) + fieldName.substring(1);
					//获取方法
					Method setMethod = clazz.getMethod(methodName, dfields[i].getType());
					//要将值设置给对象
					setMethod.invoke(bean, fieldValue);
				}
				//然后将对象添加到List
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
