/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ChapterDAOJdbcImpl.java
 * @Time May 16, 2016 5:26:24 PM
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

import cn.edu.ustb.sem.datastructure.dao.course.ChapterDAO;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class ChapterDAOJdbcImpl extends ChapterDAO {
	private static Logger logger = Logger.getLogger(ChapterDAOJdbcImpl.class);

	public static List<Chapter> findAll() {
		DatabaseConnection dbConn;
		List<Chapter> chapters = null;
		try {
		    dbConn = new DatabaseConnection();
		    Connection conn = dbConn.getConnection();
		    Statement stat = conn.createStatement();
		    ResultSet rs = stat.executeQuery("SELECT * FROM chapter ORDER BY id ASC");
		    chapters = new ArrayList<Chapter>();
		    while (rs.next()) {
		        Chapter chapter = new Chapter();
		        chapter.setId(rs.getInt("id"));
		        chapter.setChineseId(rs.getString("chinese_id"));
		        chapter.setName(rs.getString("name"));
		        chapter.setStatus(rs.getInt("status"));
		        chapters.add((Chapter) chapter);
		    }
		    dbConn.close();
		} catch (Exception e) {
		    logger.debug("Database Connection Error while trying to select all the chapters");
		    e.printStackTrace();
		}
		return chapters;
	}
	
	public static Chapter findById(int id) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Chapter chapter = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"SELECT * FROM chapter WHERE id=?");
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				chapter = new Chapter();
				chapter.setId(rs.getInt("id"));
				chapter.setChineseId(rs.getString("chinese_id"));
				chapter.setName(rs.getString("name"));
				chapter.setStatus(rs.getInt("status"));
			} else {
				logger.debug("No such chapter: id=" + id + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the chapter by id");
			e.printStackTrace();
		}
		return chapter;
	}
	
	public static boolean update(Chapter chapter) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement(
					"UPDATE chapter SET chinese_id=?, name=?, status=? WHERE id=?");
			prep.setString(1, chapter.getChineseId());
			prep.setString(2, chapter.getName());
			prep.setInt(3, chapter.getStatus());
			prep.setInt(4, chapter.getId());
			if (prep.executeUpdate() > 0) {
				logger.debug("Updated a chapter: " + chapter.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return true;
			} else {
				logger.info("Update a chapter failed: " + chapter.toString() + "\n Using SQL: "
						+ prep.toString());
				dbConn.close();
				return false;
			}
		} catch (Exception e) {
			logger.info("Update a chapter failed: " + chapter.toString() + "\n Using SQL: "
					+ prep.toString());
			logger.debug("Database Error while trying to update a chapter");
			e.printStackTrace();
		}
		return false;
	}

	
}
