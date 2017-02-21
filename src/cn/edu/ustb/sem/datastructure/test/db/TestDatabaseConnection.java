/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File TestDatabaseConnection.java
 * @Time Dec 12, 2015 3:29:49 PM
 * @Author Smile
 * @Description Test database connection
 */
package cn.edu.ustb.sem.datastructure.test.db;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description Test database connection, try to connect to the database. Throws
 *              exception if failed, while print log if succeeded.
 */
public class TestDatabaseConnection {
	private static Logger logger = Logger.getLogger(TestDatabaseConnection.class);
	public static void main(String[] args) throws Exception {
		DatabaseConnection dbConn = new DatabaseConnection();
		logger.info("Database connected as "+dbConn.getConnection());
	}
}
