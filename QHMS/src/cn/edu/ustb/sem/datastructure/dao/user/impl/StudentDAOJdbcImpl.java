/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File StudentDAOJdbcImpl.java
 * @Time Dec 12, 2015 8:34:13 PM
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
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;

/**
 * @author Smile
 * @Description
 */
public class StudentDAOJdbcImpl extends UserDAO {
	private static Logger logger = Logger.getLogger(StudentDAOJdbcImpl.class);

	/**
	 * @author Smile
	 * @Description Find all the users
	 * @return A list of Users
	 */
	public static List<User> findAll() {
		DatabaseConnection dbConn;
		List<User> students = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT * FROM user WHERE type=" + UserType.student.getValue());
			students = new ArrayList<User>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setClass(rs.getString("studentClass"));
				student.setGroup(rs.getInt("team"));
				student.setType(rs.getInt("type"));
				students.add((User) student);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the students");
			e.printStackTrace();
		}
		return students;
	}

	/**
	 * @author Smile
	 * @Description Find the students of a certain class
	 * @param userClass
	 * @return A list of Users
	 */
	public static List<User> findByClass(String studentClass) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<User> students = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM user WHERE studentClass=? AND type="
					+ UserType.student.getValue());
			prep.setString(1, studentClass);
			ResultSet rs = prep.executeQuery();
			students = new ArrayList<User>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setClass(rs.getString("studentClass"));
				student.setGroup(rs.getInt("team"));
				student.setType(rs.getInt("type"));
				students.add((User) student);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the students by class");
			e.printStackTrace();
		}
		return students;
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
		List<User> students = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM user WHERE team=? AND type=" + UserType.student.getValue());
			prep.setInt(1, group);
			ResultSet rs = prep.executeQuery();
			students = new ArrayList<User>();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setClass(rs.getString("studentClass"));
				student.setGroup(rs.getInt("team"));
				student.setType(rs.getInt("type"));
				students.add((User) student);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the students by group");
			e.printStackTrace();
		}
		return students;
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
		Student student = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM user WHERE id=? AND type=" + UserType.student.getValue());
			prep.setString(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setClass(rs.getString("studentClass"));
				student.setGroup(rs.getInt("team"));
				student.setType(rs.getInt("type"));
			} else {
				logger.debug("No such student: id=" + id + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the student by id");
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * @author Smile
	 * @Description Insert a user into database and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean insert(User student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?)");
			prep.setString(1, student.getId());
			prep.setString(2, student.getName());
			prep.setString(3, student.getPassword());
			prep.setString(4, student.getStudentClass());
			if (student.getGroup() == null) {
				prep.setNull(5, Types.INTEGER);
			} else {
				prep.setInt(5, student.getGroup());
			}
			prep.setInt(6, student.getType());
			if (prep.executeUpdate() > 0) {
				logger.debug("Inserted a student: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Insert a student failed: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Insert a student failed: " + student.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a student");
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
	public boolean delete(User student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"DELETE FROM user WHERE id=? AND type=" + UserType.student.getValue());
			prep.setString(1, student.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Deleted a student: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Delete a student failed: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Delete a student failed: " + student.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to delete a student");
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
	public boolean update(User student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET name=?, password=?, studentClass=?, team=?, type=? WHERE id=? AND type="
							+ UserType.student.getValue());
			prep.setString(1, student.getName());
			prep.setString(2, student.getPassword());
			prep.setString(3, student.getStudentClass());
			if (student.getGroup() == null) {
				prep.setNull(4, Types.INTEGER);
			} else {
				prep.setInt(4, student.getGroup());
			}
			prep.setInt(5, student.getType());
			prep.setString(6, student.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a student: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a student failed: " + student.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a student failed: " + student.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to update a student");
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
	public boolean updateWithoutPassword(User student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET name=?, studentClass=?, team=?, type=? WHERE id=? AND type="
							+ UserType.student.getValue());
			prep.setString(1, student.getName());
			prep.setString(2, student.getStudentClass());
			if (student.getGroup() == null) {
				prep.setNull(3, Types.INTEGER);
			} else {
				prep.setInt(3, student.getGroup());
			}
			prep.setInt(4, student.getType());
			prep.setString(5, student.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a student without password: " + student.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a student without password failed: " + student.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a student without password failed: " + student.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to update a student");
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
		Student student = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM user WHERE id=? AND password=? AND type="
					+ UserType.student.getValue());
			prep.setString(1, user.getId());
			prep.setString(2, user.getPassword());
			ResultSet rs = prep.executeQuery();
			student = new Student();
			if (rs.next()) {
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setClass(rs.getString("studentClass"));
				student.setGroup(rs.getInt("team"));
				student.setType(rs.getInt("type"));
				student.setValid(true);
				student.setPassword("");
				logger.info("Login succeeded:" + student.toString());
			} else {
				student.setValid(false);
				logger.info("Wrong id or password!" + student.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.info("Database Connection Error while trying to login student " + user.getId()
					+ " with password " + user.getPassword());
			e.printStackTrace();
		}
		return student;
	}
	
	/**
	 * @author Smile
	 * @Description set user to his group
	 * @param user
	 * @return updated or not
	 */
	public boolean setGroup(User student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE user SET team=? WHERE id=? AND type="
							+ UserType.student.getValue());
			if (student.getGroup() == null) {
				prep.setNull(1, Types.INTEGER);
			} else {
				prep.setInt(1, student.getGroup());
			}
			prep.setString(2, student.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Set a user to a group: " + student.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Set a user to a group failed: " + student.toString()
						+ "\n Using SQL: " + prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Set a user to a group failed: " + student.toString()
					+ "\n Using SQL: " + prep.toString());
			logger.debug("Database Error while trying to set a user to a group.");
			e.printStackTrace();
		}
		return false;
	}
}
