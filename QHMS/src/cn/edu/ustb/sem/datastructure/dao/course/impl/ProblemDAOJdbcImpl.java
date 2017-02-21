/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemDAOJdbcImpl.java
 * @Time May 15, 2016 9:10:41 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.ProblemDAO;
import cn.edu.ustb.sem.datastructure.po.course.Problem;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class ProblemDAOJdbcImpl extends ProblemDAO {
	private static Logger logger = Logger.getLogger(ProblemDAOJdbcImpl.class);

	/**
	 * @author Smile
	 * @Description Find all the problems
	 * @return A list of Problems
	 */
	public static List<Problem> findAll() {
		DatabaseConnection dbConn;
		List<Problem> problems = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM problem ORDER BY chapter_id ASC, type_id ASC, id ASC");
			problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("id"));
				problem.setTypeId(rs.getInt("type_id"));
				problem.setChapterId(rs.getInt("chapter_id"));
				problem.setDescription(rs.getString("description"));
				problem.setOption(rs.getString("given_option"));
				problem.setAnswer(rs.getString("answer"));
				problem.setPoint(rs.getInt("default_point"));
				problem.setAuthor(rs.getString("author_id"));
				problems.add((Problem) problem);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the problems");
			e.printStackTrace();
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find the problems of a certain class
	 * @param problemClass
	 * @return A list of Problems
	 */
	public static List<Problem> findByChapter(int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Problem> problems = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM problem WHERE chapter_id=? ORDER BY type_id ASC, id ASC");
			prep.setInt(1, chapter);
			ResultSet rs = prep.executeQuery();
			problems = new ArrayList<Problem>();
			while (rs.next()) {
				Problem problem = new Problem();
				problem.setId(rs.getInt("id"));
				problem.setTypeId(rs.getInt("type_id"));
				problem.setChapterId(rs.getInt("chapter_id"));
				problem.setDescription(rs.getString("description"));
				problem.setOption(rs.getString("given_option"));
				problem.setAnswer(rs.getString("answer"));
				problem.setPoint(rs.getInt("default_point"));
				problem.setAuthor(rs.getString("author_id"));
				problems.add((Problem) problem);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the problems by chapter");
			e.printStackTrace();
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find a problem by id
	 * @param id
	 * @return A problem with the input id
	 */
	public static Problem findById(int id) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Problem problem = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM problem WHERE id=?");
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				problem = new Problem();
				problem.setId(rs.getInt("id"));
				problem.setTypeId(rs.getInt("type_id"));
				problem.setChapterId(rs.getInt("chapter_id"));
				problem.setDescription(rs.getString("description"));
				problem.setOption(rs.getString("given_option"));
				problem.setAnswer(rs.getString("answer"));
				problem.setPoint(rs.getInt("default_point"));
				problem.setAuthor(rs.getString("author_id"));
			} else {
				logger.debug("No such problem: id=" + id + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the problem by id");
			e.printStackTrace();
		}
		return problem;
	}

	/**
	 * @author Smile
	 * @Description Find all the problems
	 * @return A list of Problems
	 */
	public static List<Problem> findAllWithoutAnswer() {
		List<Problem> problems=findAll();
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAnswer(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find the problems of a certain class
	 * @param problemClass
	 * @return A list of Problems
	 */
	public static List<Problem> findByChapterWithoutAnswer(int chapter) {
		List<Problem> problems=findByChapter(chapter);
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAnswer(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find a problem by id
	 * @param id
	 * @return A problem with the input id
	 */
	public static Problem findByIdWithoutAnswer(int id) {
		Problem problem = findById(id);
		problem.setAnswer(null);
		return problem;
	}
	/**
	 * @author Smile
	 * @Description Find all the problems
	 * @return A list of Problems
	 */
	public static List<Problem> findAllWithoutAuthor() {
		List<Problem> problems=findAll();
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAuthor(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find the problems of a certain class
	 * @param problemClass
	 * @return A list of Problems
	 */
	public static List<Problem> findByChapterWithoutAuthor(int chapter) {
		List<Problem> problems=findByChapter(chapter);
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAuthor(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find a problem by id
	 * @param id
	 * @return A problem with the input id
	 */
	public static Problem findByIdWithoutAuthor(int id) {
		Problem problem = findById(id);
		problem.setAuthor(null);
		return problem;
	} 
	/**
	 * @author Smile
	 * @Description Find all the problems
	 * @return A list of Problems
	 */
	public static List<Problem> findAllWithoutAuthorAndAnswer() {
		List<Problem> problems=findAll();
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAuthor(null);
			problems.get(i).setAnswer(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find the problems of a certain class
	 * @param problemClass
	 * @return A list of Problems
	 */
	public static List<Problem> findByChapterWithoutAuthorAndAnswer(int chapter) {
		List<Problem> problems=findByChapter(chapter);
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAuthor(null);
			problems.get(i).setAnswer(null);
		}
		return problems;
	}

	/**
	 * @author Smile
	 * @Description Find a problem by id
	 * @param id
	 * @return A problem with the input id
	 */
	public static Problem findByIdWithoutAuthorAndAnswer(int id) {
		Problem problem = findById(id);
		problem.setAuthor(null);
		problem.setAnswer(null);
		return problem;
	}  
	/**
	 * @author Smile
	 * @Description Insert a problem into database and log this action
	 * @param problem
	 * @return success or not
	 */
	public static boolean insert(Problem problem) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO problem VALUES(null,?,?,?,?,?,?,?)");
			prep.setInt(1, problem.getTypeId());
			prep.setInt(2, problem.getChapterId());
			prep.setString(3, problem.getDescription());
			prep.setString(4, problem.getOption());
			prep.setString(5, problem.getAnswer());
			prep.setInt(6, problem.getPoint());
			prep.setString(7, problem.getAuthor());
			if (prep.executeUpdate() > 0) {
				logger.debug("Inserted a problem: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Insert a problem failed: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Insert a problem failed: " + problem.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a problem");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Delete a problem from database and log this action
	 * @param problem
	 * @return success or not
	 */
	public static boolean delete(Problem problem) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"DELETE FROM problem WHERE id=?");
			prep.setInt(1, problem.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Deleted a problem: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Delete a problem failed: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Delete a problem failed: " + problem.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to delete a problem");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a problem and log this action
	 * @param problem
	 * @return success or not
	 */
	public static boolean update(Problem problem) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE problem SET type_id=?, chapter_id=?, description=?, given_option=?, answer=?, default_point=? WHERE id=?");
			prep.setInt(1, problem.getTypeId());
			prep.setInt(2, problem.getChapterId());
			prep.setString(3, problem.getDescription());
			prep.setString(4, problem.getOption());
			prep.setString(5, problem.getAnswer());
			prep.setInt(6, problem.getPoint());
			prep.setInt(7, problem.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a problem: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a problem failed: " + problem.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a problem failed: " + problem.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to update a problem");
			e.printStackTrace();
		}
		return false;
	}

}
