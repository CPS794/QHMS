/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File SystemVariableDAOJdbcImpl.java
 * @Time Mar 9, 2016 4:18:00 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.system.SystemVariableDAO;
import cn.edu.ustb.sem.datastructure.po.system.SystemVariable;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class SystemVariableDAOJdbcImpl extends SystemVariableDAO {
	private static Logger logger = Logger.getLogger(SystemVariableDAOJdbcImpl.class);

	public static List<SystemVariable> findAll() {
		DatabaseConnection dbConn;
		List<SystemVariable> systemVariables = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM system_variable");
			systemVariables = new ArrayList<SystemVariable>();
			while (rs.next()) {
				SystemVariable systemVariable = new SystemVariable();
				systemVariable.setName(rs.getString("name"));
				systemVariable.setChineseName(rs.getString("chinese_name"));
				systemVariable.setValue(rs.getString("value"));
				systemVariables.add(systemVariable);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the system variables");
			e.printStackTrace();
		}
		return systemVariables;
	}

	public static SystemVariable findById(String name) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		SystemVariable systemVariable = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM system_variable WHERE name=?");
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				systemVariable = new SystemVariable();
				systemVariable.setName(rs.getString("name"));
				systemVariable.setChineseName(rs.getString("chinese_name"));
				systemVariable.setValue(rs.getString("value"));
			} else {
				logger.debug("No such system_variable: name=" + name + "\n Using SQL: "
						+ prep.toString());
			}
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select the system_variable by name");
			e.printStackTrace();
		}
		return null;
	}

	public static boolean insert(SystemVariable systemVariable) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO system_variable VALUES(?,?,?)");
			prep.setString(1, systemVariable.getName());
			prep.setString(2, systemVariable.getChineseName());
			prep.setString(3, systemVariable.getValue());
		} catch (Exception e) {
			logger.info("Insert a admin failed: " + systemVariable.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a system_variable");
			e.printStackTrace();
		}
		return false;
	}

	public static boolean update(SystemVariable systemVariable) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE system_variable SET chinese_name=?, value=? WHERE name=?");
			prep.setString(3, systemVariable.getName());
			prep.setString(1, systemVariable.getChineseName());
			prep.setString(2, systemVariable.getValue());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a system_variable: " + systemVariable.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a system_variable failed: " + systemVariable.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a system_variable failed: " + systemVariable.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to update a system_variable");
			e.printStackTrace();
		}
		return false;
	}

	public static boolean delete(SystemVariable systemVariable) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("DELETE FROM system_variable WHERE name=?");
			prep.setString(1, systemVariable.getName());
			if (prep.executeUpdate() > 0) {
				logger.debug("Deleted a system_variable: " + systemVariable.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Delete a system_variable failed: " + systemVariable.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Delete a system_variable failed: " + systemVariable.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to delete a system_variable");
			e.printStackTrace();
		}
		return false;
	}
}
