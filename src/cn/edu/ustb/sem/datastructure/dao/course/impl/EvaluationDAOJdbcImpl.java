/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File EvaluationDAOJdbcImpl.java
 * @Time May 30, 2016 7:51:08 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.EvaluationDAO;
import cn.edu.ustb.sem.datastructure.po.course.Evaluation;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class EvaluationDAOJdbcImpl extends EvaluationDAO {
	private static Logger logger = Logger.getLogger(EvaluationDAOJdbcImpl.class);

	public static List<Evaluation> findNotFinished() {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Evaluation> evaluations = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM score_percentage WHERE type=0 AND used=1 ORDER BY id ASC");
			ResultSet rs = prep.executeQuery();
			evaluations = new ArrayList<Evaluation>();
			while (rs.next()) {
				Evaluation evaluation = new Evaluation();
				evaluation.setScorePercentageId(rs.getInt("id"));
				evaluation.setScoreName(rs.getString("name"));
				evaluations.add((Evaluation) evaluation);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the evaluations not finished");
			e.printStackTrace();
		}
		return evaluations;
	}
	
	public static List<Evaluation> findByStudent(String studentId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Evaluation> evaluations = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM evaluate,score_percentage WHERE evaluate.score_percentage_id=score_percentage.id AND student_id=? ORDER BY chapter_id ASC, score_percentage_id ASC");
			prep.setString(1, studentId);
			ResultSet rs = prep.executeQuery();
			evaluations = new ArrayList<Evaluation>();
			while (rs.next()) {
				Evaluation evaluation = new Evaluation();
				evaluation.setStudentId(rs.getString("student_id"));
				evaluation.setChapterId(rs.getInt("chapter_id"));
				evaluation.setScorePercentageId(rs.getInt("score_percentage_id"));
				evaluation.setScoreName(rs.getString("name"));
				evaluation.setPoint(rs.getInt("point"));
				evaluations.add((Evaluation) evaluation);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the evaluations by student");
			e.printStackTrace();
		}
		return evaluations;
	}

	public static List<Evaluation> findByStudentAndChapter(String studentId, int chapterId) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Evaluation> evaluations = new ArrayList<Evaluation>();
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM evaluate,score_percentage WHERE evaluate.score_percentage_id=score_percentage.id AND student_id=? AND chapter_id=? ORDER BY score_percentage_id ASC");
			prep.setString(1, studentId);
			prep.setInt(2, chapterId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Evaluation evaluation = new Evaluation();
				evaluation.setStudentId(rs.getString("student_id"));
				evaluation.setChapterId(rs.getInt("chapter_id"));
				evaluation.setScorePercentageId(rs.getInt("score_percentage_id"));
				evaluation.setScoreName(rs.getString("name"));
				evaluation.setPoint(rs.getInt("point"));
				evaluations.add((Evaluation) evaluation);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the evaluations by student");
			e.printStackTrace();
		}
		return evaluations;
	}

	public static boolean insert(Evaluation evaluation) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("INSERT INTO evaluate VALUES(?,?,?,?)");
			prep.setString(1, evaluation.getStudentId());
			prep.setInt(2, evaluation.getChapterId());
			prep.setInt(3, evaluation.getScorePercentageId());
			prep.setInt(4, evaluation.getPoint());
			if (prep.executeUpdate() > 0) {
				logger.debug("Inserted a evaluation: " + evaluation.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Insert a evaluation failed: " + evaluation.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Insert a evaluation failed: " + evaluation.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to insert a evaluation");
			e.printStackTrace();
		}
		return false;
	}


}
