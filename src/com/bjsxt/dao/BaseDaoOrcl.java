package com.bjsxt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDaoOrcl {
/**
 * 提取数据库连接的方法
 * */
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@192.168.3.35:1521:ORCL";
	private static final String USERNAME="match";
	private static final String PWD="123";
	
	public static Connection getConn(){
		Connection connection=null;
		try {
			Class.forName(DRIVER);
			connection= DriverManager.getConnection(URL, USERNAME, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	
	public static void closeAll(ResultSet rSet,Statement stat,Connection conn){
		if(rSet!=null){
			try {
				rSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
