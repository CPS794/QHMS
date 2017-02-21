/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File DatabaseConnection.java
 * @Time Dec 12, 2015 3:13:56 PM
 * @Author Smile
 * @Description Database Connector
 */
package cn.edu.ustb.sem.datastructure.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Smile
 * @Description Database Connector, connect with MySQL
 */
public class DatabaseConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/QHMSDB?useUnicode=true&characterEncoding=utf-8";
	private static final String DB_USER = "qhmsmanager";
	private static final String DB_PASSWORD = "dbmanage";
	private Connection conn = null;

	/**
	 * @Description Set connection with url, user and password
	 * @throws Exception
	 */
	public DatabaseConnection() throws Exception {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	/**
	 * @author Smile
	 * @Description Get database connection
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		return conn;
	}

	/**
	 * @author Smile
	 * @Description Close connection
	 * @throws Exception
	 */
	public void close() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}
