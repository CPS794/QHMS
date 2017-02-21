/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemTypeDAOJdbcImpl.java
 * @Time May 16, 2016 7:03:29 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.ProblemTypeDAO;
import cn.edu.ustb.sem.datastructure.po.course.ProblemType;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class ProblemTypeDAOJdbcImpl extends ProblemTypeDAO {
	private static Logger logger = Logger.getLogger(ProblemTypeDAOJdbcImpl.class);

	public static List<ProblemType> findAll() {
		DatabaseConnection dbConn;
		List<ProblemType> problemTypes = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM problem_type ORDER BY id ASC");
			problemTypes = new ArrayList<ProblemType>();
			while (rs.next()) {
				ProblemType problemType = new ProblemType();
				problemType.setId(rs.getInt("id"));
				problemType.setName(rs.getString("chinese_name"));
				problemType.setPoint(rs.getInt("default_point"));
				problemType.setJudge(rs.getInt("judge"));
				problemTypes.add((ProblemType) problemType);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the problemTypes");
			e.printStackTrace();
		}
		return problemTypes;
	}
}
