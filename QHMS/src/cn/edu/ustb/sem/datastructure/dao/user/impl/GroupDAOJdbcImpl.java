/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GroupDAOJdbcImpl.java
 * @Time May 15, 2016 11:02:48 AM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.user.GroupDAO;
import cn.edu.ustb.sem.datastructure.po.user.Group;
import cn.edu.ustb.sem.datastructure.util.DatabaseConnection;

/**
 * @author Smile
 * @Description
 */
public class GroupDAOJdbcImpl extends GroupDAO {
	private static Logger logger = Logger.getLogger(GroupDAO.class);

	/**
	 * @author Smile
	 * @Description Find all the groups
	 * @return A list of Groups
	 */
	public List<Group> findAll() {
		DatabaseConnection dbConn;
		List<Group> groups = null;
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM team");
			groups = new ArrayList<Group>();
			while (rs.next()) {
				Group group = new Group();
				group.setId(rs.getInt("id"));
				group.setName(rs.getString("name"));
				group.setChapterId(rs.getInt("chapter_id"));
				groups.add(group);
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select all the groups");
			e.printStackTrace();
		}
		return groups;
	}

	/**
	 * @author Smile
	 * @Description Find a group by id
	 * @param id
	 * @return A group with the input id
	 */
	public Group findById(int id) {
		DatabaseConnection dbConn;
		PreparedStatement prep = null;
		Group group = new Group();
		try {
			dbConn = new DatabaseConnection();
			Connection conn = dbConn.getConnection();
			prep = conn.prepareStatement("SELECT * FROM user WHERE id=?");
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				group.setId(rs.getInt("id"));
				group.setName(rs.getString("name"));
				group.setChapterId(rs.getInt("chapter_id"));
			} else {
				logger.debug("No such group: id=" + id + "\n Using SQL: " + prep.toString());
			}
			dbConn.close();
		} catch (Exception e) {
			logger.debug("Database Connection Error while trying to select the group by id");
			e.printStackTrace();
		}
		return group;
	}

	public boolean insert(Group group) {
		return false;
	}

	public boolean delete(Group group) {
		return false;
	}

	public boolean update(Group group) {
		return false;
	}

	public static Group getMembers(int groupId) {
		Group group= new Group();
		group.setMembers(StudentDAOJdbcImpl.findByGroup(groupId));
		return group;
	}

}
