/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GroupDAO.java
 * @Time May 15, 2016 10:54:58 AM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.user;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.user.Group;

/**
 * @author Smile
 * @Description
 */
public abstract class GroupDAO {
	/**
	 * @author Smile
	 * @Description
	 * @return
	 */
	public List<Group> findAll() {
		return null;
	};

	/**
	 * @author Smile
	 * @Description
	 * @param id
	 * @return
	 */
	public Group findById(int id) {
		return null;
	};

	/**
	 * @author Smile
	 * @Description
	 * @param group
	 * @return
	 */
	public boolean insert(Group group) {
		return false;
	};

	/**
	 * @author Smile
	 * @Description
	 * @param group
	 * @return
	 */
	public boolean delete(Group group) {
		return false;
	};

	/**
	 * @author Smile
	 * @Description
	 * @param group
	 * @return
	 */
	public boolean update(Group group) {
		return false;
	};

	/**
	 * @author Smile
	 * @Description
	 * @param group
	 * @return
	 */
	public static Group getMembers(int groupId) {
		return null;
	};
}
