package com.abc.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection conn;
	
	public static Connection getConn() {
		
		try {
			
			//step:1 for connection - load the driver class 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//step:2- create a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc_lab_db","root","123456");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return conn;
	}
}
