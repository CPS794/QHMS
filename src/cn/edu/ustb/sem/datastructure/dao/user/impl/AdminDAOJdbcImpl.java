/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File AdminDAOJdbcImpl.java
 * @Time Mar 4, 2016 8:09:26 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.po.user.Admin;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;

/**
 * @author Smile
 * @Description
 */
public class AdminDAOJdbcImpl extends UserDAO {
	private static Logger logger = Logger.getLogger(AdminDAOJdbcImpl.class);

	/**
	 * @author Smile
	 * @Description Find all the users
	 * @return A list of Users
	 */
	public static List<User> findAll() {
		DatabaseConnection dbConn;
		List<User> admins = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs =
					stat.executeQuery("SELECT * FROM user WHERE type=" + UserType.admin.getValue());
			admins = new ArrayList<User>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setClass(rs.getString("studentClass"));
				admin.setGroup(rs.getInt("team"));
				admin.setType(rs.getInt("type"));
				admins.add((User) admin);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the admins");
			e.printStackTrace();
		}
		return admins;
	}

	/**
	 * @author Smile
	 * @Description Find the admins of a certain class
	 * @param userClass
	 * @return A list of Users
	 */
	public static List<User> findByClass(String studentClass) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<User> admins = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM user WHERE studentClass=? AND type=" + UserType.admin.getValue());
			prep.setString(1, studentClass);
			ResultSet rs = prep.executeQuery();
			admins = new ArrayList<User>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setClass(rs.getString("studentClass"));
				admin.setGroup(rs.getInt("team"));
				admin.setType(rs.getInt("type"));
				admins.add((User) admin);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the admins by class");
			e.printStackTrace();
		}
		return admins;
	}

	/**
	 * @author Smile
	 * @Description Find the users of a certain group
	 * @param group
	 * @return A list of Users
	 */
	public static List<User> findByGroup(int group) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<User> admins = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM user WHERE team=? AND type=" + UserType.admin.getValue());
			prep.setInt(1, group);
			ResultSet rs = prep.executeQuery();
			admins = new ArrayList<User>();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setClass(rs.getString("studentClass"));
				admin.setGroup(rs.getInt("team"));
				admin.setType(rs.getInt("type"));
				admins.add((User) admin);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the admins by group");
			e.printStackTrace();
		}
		return admins;
	}

	/**
	 * @author Smile
	 * @Description Find a user by id
	 * @param id
	 * @return A user with the input id
	 */
	public User findById(String id) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Admin admin = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM user WHERE id=? AND type=" + UserType.admin.getValue());
			prep.setString(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setClass(rs.getString("studentClass"));
				admin.setGroup(rs.getInt("team"));
				admin.setType(rs.getInt("type"));
			} else {
				logger.debug("No such admin: id=" + id + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the admin by id");
			e.printStackTrace();
		}
		return admin;
	}

	/**
	 * @author Smile
	 * @Description Insert a user into database and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean insert(User admin) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?)");
			prep.setString(1, admin.getId());
			prep.setString(2, admin.getName());
			prep.setString(3, admin.getPassword());
			prep.setString(4, admin.getStudentClass());
			if (admin.getGroup() == null) {
				prep.setNull(5, Types.INTEGER);
			} else {
				prep.setInt(5, admin.getGroup());
			}
			prep.setInt(6, admin.getType());
			if (prep.executeUpdate() > 0) {
				logger.debug("Inserted a admin: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Insert a admin failed: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Insert a admin failed: " + admin.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a admin");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Delete a user from database and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean delete(User admin) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"DELETE FROM user WHERE id=? AND type=" + UserType.admin.getValue());
			prep.setString(1, admin.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Deleted a admin: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Delete a admin failed: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Delete a admin failed: " + admin.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to delete a admin");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a user and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean update(User admin) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET name=?, password=?, studentClass=?, team=?, type=? WHERE id=? AND type="
							+ UserType.admin.getValue());
			prep.setString(1, admin.getName());
			prep.setString(2, admin.getPassword());
			prep.setString(3, admin.getStudentClass());
			if (admin.getGroup() == null) {
				prep.setNull(4, Types.INTEGER);
			} else {
				prep.setInt(4, admin.getGroup());
			}
			prep.setInt(5, admin.getType());
			prep.setString(6, admin.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a admin: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a admin failed: " + admin.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a admin failed: " + admin.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to update a admin");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a user without his/her password and log this action,
	 *              that means, let the password be and update other information
	 * @param user
	 * @return success or not
	 */
	public boolean updateWithoutPassword(User admin) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET name=?, studentClass=?, team=?, type=? WHERE id=? AND type="
							+ UserType.admin.getValue());
			prep.setString(1, admin.getName());
			prep.setString(2, admin.getStudentClass());
			if (admin.getGroup() == null) {
				prep.setNull(3, Types.INTEGER);
			} else {
				prep.setInt(3, admin.getGroup());
			}
			prep.setInt(4, admin.getType());
			prep.setString(5, admin.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a admin without password: " + admin.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a admin without password failed: " + admin.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a admin without password failed: " + admin.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to update a admin");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Check whether the user is valid and log this action
	 * @param user
	 * @return a valid user or an empty one
	 */
	public User login(User user) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Admin admin = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM user WHERE id=? AND password=? AND type="
					+ UserType.admin.getValue());
			prep.setString(1, user.getId());
			prep.setString(2, user.getPassword());
			ResultSet rs = prep.executeQuery();
			admin = new Admin();
			if (rs.next()) {
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setClass(rs.getString("StudentClass")); // Here I use "StudentClass" in DB! Whether it's admin or anyone else!
				admin.setGroup(rs.getInt("team"));
				admin.setType(rs.getInt("type"));
				admin.setValid(true);
				admin.setPassword("");
				logger.info("Login succeeded:" + admin.toString());
			} else {
				admin.setValid(false);
				logger.info("Wrong id or password!" + admin.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.info("Database Connection Error while trying to login admin " + user.getId()
					+ " with password " + user.getPassword());
			e.printStackTrace();
		}
		return admin;
	}
	
	/**
	 * @author Smile
	 * @Description set user to his group
	 * @param user
	 * @return updated or not
	 */
	public boolean setGroup(User admin) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET team=? WHERE id=? AND type="
							+ UserType.admin.getValue());
			if (admin.getGroup() == null) {
				prep.setNull(1, Types.INTEGER);
			} else {
				prep.setInt(1, admin.getGroup());
			}
			prep.setString(2, admin.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Set a user to a group: " + admin.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Set a user to a group failed: " + admin.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Set a user to a group failed: " + admin.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to set a user to a group.");
			e.printStackTrace();
		}
		return false;
	}
}
