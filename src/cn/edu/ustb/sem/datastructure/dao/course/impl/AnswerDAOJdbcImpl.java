/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File AnswerDAOJdbcImpl.java
 * @Time May 17, 2016 11:36:42 PM
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

import cn.edu.ustb.sem.datastructure.dao.course.AnswerDAO;
import cn.edu.ustb.sem.datastructure.po.course.Answer;
import cn.edu.ustb.sem.datastructure.po.course.ProblemAnswer;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class AnswerDAOJdbcImpl extends AnswerDAO {
	private static Logger logger = Logger.getLogger(AnswerDAOJdbcImpl.class);

	/**
	 * @author Smile
	 * @Description Find all the answers
	 * @return A list of Answers
	 */
	public static List<Answer> findAll() {
		DatabaseConnection dbConn;
		List<Answer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM answer ORDER BY problemId ASC");
			answers = new ArrayList<Answer>();
			while (rs.next()) {
				Answer answer = new Answer();
				answer.setStudentId(rs.getString("student_id"));
				answer.setProblemId(rs.getInt("problem_id"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPoint(rs.getInt("point"));
				answer.setTeacherId(rs.getString("teacher_id"));
				answers.add((Answer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the answers");
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a certain chapter
	 * @param chapter
	 * @return A list of Answers
	 */
	public static List<Answer> findByChapter(int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Answer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM answer,problem WHERE answer.problem_id=problem.id AND problem.chapter_id=? ORDER BY student_id ASC");
			prep.setInt(1, chapter);
			ResultSet rs = prep.executeQuery();
			answers = new ArrayList<Answer>();
			while (rs.next()) {
				Answer answer = new Answer();
				answer.setStudentId(rs.getString("student_id"));
				answer.setProblemId(rs.getInt("problem_id"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPoint(rs.getInt("point"));
				answer.setTeacherId(rs.getString("teacher_id"));
				answers.add((Answer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a certain chapter
	 * @param chapter
	 * @return A list of ProblemAnswers
	 */
	public static List<ProblemAnswer> findProblemAnswerByChapter(int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<ProblemAnswer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM answer,problem,problem_type WHERE answer.problem_id=problem.id AND problem.type_id=problem_type.id AND problem.chapter_id=? AND problem_type.judge=1 ORDER BY problem.id ASC");
			prep.setInt(1, chapter);
			ResultSet rs = prep.executeQuery();
			answers = new ArrayList<ProblemAnswer>();
			while (rs.next()) {
				ProblemAnswer answer = new ProblemAnswer();
				answer.setStudentId(rs.getString("answer.student_id"));
				answer.setProblemId(rs.getInt("answer.problem_id"));
				answer.setAnswer(rs.getString("answer.answer"));
				answer.setPoint(rs.getInt("answer.point"));
				answer.setTeacherId(rs.getString("answer.teacher_id"));
				answer.setTypeId(rs.getInt("problem.type_id"));
				answer.setDescription(rs.getString("problem.description"));
				answer.setProblemAnswer(rs.getString("problem.answer"));
				answer.setOption(rs.getString("problem.given_option"));
				answer.setFullPoint(rs.getInt("problem.default_point"));
				answer.setChapterId(rs.getInt("problem.chapter_id"));
				answers.add((ProblemAnswer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answers;
	}

	public static List<ProblemAnswer> findProblemWrongAnswerByChapter(int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<ProblemAnswer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT user.id AS student_id, user.name AS student_name, problem.id AS problem_id, problem_type.id AS problem_type, problem.answer AS problem_answer, answer.answer AS answer, answer.point AS point, problem.default_point AS full_point FROM answer, problem, problem_type, user WHERE problem_type.judge=0 AND problem.type_id = problem_type.id AND answer.problem_id = problem.id AND answer.student_id=user.id AND user.type=0 AND answer.point<problem.default_point AND problem.chapter_id=? UNION SELECT user.id AS student_id, user.name AS student_name, problem.id AS problem_id, problem_type.id AS problem_type, problem.answer AS problem_answer, answer.answer AS answer, answer.point AS point, problem.default_point AS full_point FROM answer, problem, problem_type, user WHERE problem_type.judge=1 AND problem.type_id = problem_type.id AND answer.problem_id = problem.id AND answer.student_id=user.id AND user.type=0 AND answer.point<problem.default_point AND NOT(ISNULL(answer.teacher_id) OR LENGTH(answer.teacher_id)<1) AND problem.chapter_id=? ORDER BY student_id ASC,problem_type ASC, problem_id ASC");
			prep.setInt(1, chapter);
			prep.setInt(2, chapter);
			ResultSet rs = prep.executeQuery();
			answers = new ArrayList<ProblemAnswer>();
			while (rs.next()) {
				ProblemAnswer answer = new ProblemAnswer();
				answer.setStudentId(rs.getString("student_id"));
				answer.setProblemId(rs.getInt("problem_id"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPoint(rs.getInt("point"));
				answer.setTypeId(rs.getInt("problem_type"));
				answer.setProblemAnswer(rs.getString("problem_answer"));
				answer.setFullPoint(rs.getInt("full_point"));
				answer.setChapterId(chapter);
				answers.add((ProblemAnswer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a certain chapter
	 * @param chapter
	 * @return A list of ProblemAnswers
	 */
	public static List<ProblemAnswer> findAllProblemAnswerByStudentAndChapter(int chapter,
			String studentId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<ProblemAnswer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM answer,problem,problem_type WHERE answer.problem_id=problem.id AND problem.type_id=problem_type.id AND problem.chapter_id=? AND answer.student_id=? ORDER BY problem.type_id ASC, problem.id ASC");
			prep.setInt(1, chapter);
			prep.setString(2, studentId);
			ResultSet rs = prep.executeQuery();
			answers = new ArrayList<ProblemAnswer>();
			while (rs.next()) {
				ProblemAnswer answer = new ProblemAnswer();
				answer.setStudentId(rs.getString("answer.student_id"));
				answer.setProblemId(rs.getInt("answer.problem_id"));
				answer.setAnswer(rs.getString("answer.answer"));
				answer.setPoint(rs.getInt("answer.point"));
				answer.setTeacherId(rs.getString("answer.teacher_id"));
				answer.setTypeId(rs.getInt("problem.type_id"));
				answer.setDescription(rs.getString("problem.description"));
				answer.setProblemAnswer(rs.getString("problem.answer"));
				answer.setOption(rs.getString("problem.given_option"));
				answer.setFullPoint(rs.getInt("problem.default_point"));
				answer.setChapterId(rs.getInt("problem.chapter_id"));
				answers.add((ProblemAnswer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a studnent and a certain chapter
	 * @param chapter
	 * @return A list of Answers
	 */
	public static List<Answer> findByStudentAndChapter(int chapter, String studentId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Answer> answers = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM answer,problem WHERE answer.problem_id=problem.id AND problem.chapter_id=? AND answer.student_id=? ORDER BY student_id ASC");
			prep.setInt(1, chapter);
			prep.setString(2, studentId);
			ResultSet rs = prep.executeQuery();
			answers = new ArrayList<Answer>();
			while (rs.next()) {
				Answer answer = new Answer();
				answer.setStudentId(rs.getString("student_id"));
				answer.setProblemId(rs.getInt("problem_id"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPoint(rs.getInt("point"));
				answer.setTeacherId(rs.getString("teacher_id"));
				answers.add((Answer) answer);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answers;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a student and a certain chapter
	 * @param chapter
	 * @return A number of Answers
	 */
	public static int countByStudentAndChapter(int chapter, String studentId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		int answerCount = 0;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT COUNT(*) AS count_answer FROM answer,problem WHERE answer.problem_id=problem.id AND problem.chapter_id=? AND answer.student_id=? ORDER BY student_id ASC");
			prep.setInt(1, chapter);
			prep.setString(2, studentId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				answerCount = rs.getInt("count_answer");
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answerCount;
	}

	/**
	 * @author Smile
	 * @Description Find the answers of a student and a certain chapter
	 * @param chapter
	 * @return A number of Answers
	 */
	public static int countNotFinishedByStudentAndChapter(int chapter, String studentId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		int answerCount = 0;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT COUNT(*) AS count_no_remark FROM answer,problem,problem_type WHERE answer.problem_id=problem.id AND problem.type_id=problem_type.id AND problem.chapter_id=? AND answer.student_id=? AND (ISNULL(answer.teacher_id) OR LENGTH(answer.teacher_id)<1) AND problem_type.judge=1");
			prep.setInt(1, chapter);
			prep.setString(2, studentId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				answerCount = rs.getInt("count_no_remark");
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the answers by chapter");
			e.printStackTrace();
		}
		return answerCount;
	}

	/**
	 * @author Smile
	 * @Description Find a answer by id
	 * @param id
	 * @return A answer with the input id
	 */
	public static Answer findById(String studentId, int problemId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Answer answer = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM answer WHERE student_id=? AND problem_id=?");
			prep.setString(1, studentId);
			prep.setInt(2, problemId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				answer = new Answer();
				answer.setStudentId(rs.getString("student_id"));
				answer.setProblemId(rs.getInt("problem_id"));
				answer.setAnswer(rs.getString("answer"));
				answer.setPoint(rs.getInt("point"));
				answer.setTeacherId(rs.getString("teacher_id"));
			} else {
				logger.debug("No such answer: studentId=" + studentId + "and problemId=" + problemId
						+ "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the answer by id");
			e.printStackTrace();
		}
		return answer;
	}

	/**
	 * @author Smile
	 * @Description Insert a answer into database and log this action
	 * @param answer
	 * @return success or not
	 */
	public static boolean insert(Answer answer) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO answer VALUES(?,?,?,?,?)");
			prep.setString(1, answer.getStudentId());
			prep.setInt(2, answer.getProblemId());
			prep.setString(3, answer.getAnswer());
			prep.setInt(4, answer.getPoint());
			prep.setString(5, answer.getTeacherId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Inserted a answer: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Insert a answer failed: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Insert a answer failed: " + answer.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a answer");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Delete a answer from database and log this action
	 * @param answer
	 * @return success or not
	 */
	public static boolean delete(Answer answer) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("DELETE FROM answer WHERE student_id=? AND problem_id=?");
			prep.setString(1, answer.getStudentId());
			prep.setInt(2, answer.getProblemId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Deleted a answer: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Delete a answer failed: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Delete a answer failed: " + answer.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to delete a answer");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a answer and log this action
	 * @param answer
	 * @return success or not
	 */
	public static boolean update(Answer answer) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE answer SET answer=?, point=?, teacher_id=? WHERE student_id=? AND problem_id=?");
			prep.setString(1, answer.getAnswer());
			prep.setInt(2, answer.getPoint());
			prep.setString(3, answer.getTeacherId());
			prep.setString(4, answer.getStudentId());
			prep.setInt(5, answer.getProblemId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a answer: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a answer failed: " + answer.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a answer failed: " + answer.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to update a answer");
			e.printStackTrace();
		}
		return false;
	}

}
