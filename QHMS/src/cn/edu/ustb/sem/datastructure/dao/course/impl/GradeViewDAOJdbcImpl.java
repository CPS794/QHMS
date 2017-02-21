/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GradeViewDAOJdbcImpl.java
 * @Time Jul 2, 2016 3:04:46 PM
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

import cn.edu.ustb.sem.datastructure.dao.course.GradeViewDAO;
import cn.edu.ustb.sem.datastructure.po.course.Grade;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class GradeViewDAOJdbcImpl extends GradeViewDAO {
	private static Logger logger = Logger.getLogger(GradeViewDAOJdbcImpl.class);

	public static List<Grade> findAll() {
		DatabaseConnection dbConn;
		List<Grade> grades = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM grade_view ORDER BY student_id ASC, chapter_id ASC");
			grades = new ArrayList<Grade>();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setChapterId(rs.getInt("chapter_id"));
				grade.setStudentId(rs.getString("student_id"));
				grade.setPoint(rs.getDouble("chapter_grade"));
				grades.add(grade);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the grades");
			e.printStackTrace();
		}
		return grades;
	}

	public static List<Grade> findByStudent(String student) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Grade> grades = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM grade_view WHERE student_id=? ORDER BY chapter_id ASC");
			prep.setString(1, student);
			ResultSet rs = prep.executeQuery();
			grades = new ArrayList<Grade>();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setChapterId(rs.getInt("chapter_id"));
				grade.setStudentId(rs.getString("student_id"));
				grade.setPoint(rs.getDouble("chapter_grade"));
				grades.add((Grade) grade);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the grades by student");
			e.printStackTrace();
		}
		return grades;
	}

	public static List<Grade> findByChapter(int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		List<Grade> grades = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM grade_view WHERE chapter_id=? ORDER BY student_id ASC");
			prep.setInt(1, chapter);
			ResultSet rs = prep.executeQuery();
			grades = new ArrayList<Grade>();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setChapterId(rs.getInt("chapter_id"));
				grade.setStudentId(rs.getString("student_id"));
				grade.setPoint(rs.getDouble("chapter_grade"));
				grades.add((Grade) grade);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug(
					"Database Connection Error while trying to select all the grades by chapter");
			e.printStackTrace();
		}
		return grades;
	}

	public static Grade findByStudentAndChapter(String student, int chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Grade grade = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM grade WHERE student_id=? AND chapter_id=?");
			prep.setString(1, student);
			prep.setInt(2, chapter);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				grade = new Grade();
				grade.setChapterId(rs.getInt("chapter_id"));
				grade.setStudentId(rs.getString("student_id"));
				grade.setPoint(rs.getDouble("chapter_grade"));
			} else {
				logger.debug("No such grade: student_id=" + student +" chapter_id=" + chapter + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the grade by student and chapter");
			e.printStackTrace();
		}
		return grade;
	}

}
