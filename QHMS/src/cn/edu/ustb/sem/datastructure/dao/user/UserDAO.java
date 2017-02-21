/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File UserDAO.java
 * @Time Dec 12, 2015 8:16:31 PM
 * @Author Smile
 * @Description UserDAO to be implied by StudentDAO and AdminDAO
 */
package cn.edu.ustb.sem.datastructure.dao.user;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.user.User;

/**
 * @author Smile
 * @Description
 */
public abstract class UserDAO {
	/**
	 * @author Smile
	 * @Description Find all the users
	 * @return A list of Users
	 */
	public static List<User> findAll() {
		return null;
	}

	/**
	 * @author Smile
	 * @Description Find the users (usually students) of a certain class
	 * @param userClass
	 * @return A list of Users
	 */
	public static List<User> findByClass(String userClass) {
		return null;
	}

	/**
	 * @author Smile
	 * @Description Find the users of a certain group
	 * @param group
	 * @return A list of Users
	 */
	public static List<User> findByGroup(int group) {
		return null;
	}

	/**
	 * @author Smile
	 * @Description Find a user by id
	 * @param id
	 * @return A user with the input id
	 */
	public User findById(String id) {
		return null;
	}

	/**
	 * @author Smile
	 * @Description Insert a user into database and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean insert(User user) {
		return false;
	}

	/**
	 * @author Smile
	 * @Description Delete a user from database and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean delete(User user) {
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a user and log this action
	 * @param user
	 * @return success or not
	 */
	public boolean update(User user) {
		return false;
	}

	/**
	 * @author Smile
	 * @Description Update a user without his/her password and log this action,
	 *              that means, let the password be and update other information
	 * @param user
	 * @return success or not
	 */
	public boolean updateWithoutPassword(User user) {
		return false;
	}

	/**
	 * @author Smile
	 * @Description Check whether the user is valid and log this action
	 * @param user
	 * @return a valid user or an empty one
	 */
	public User login(User user) {
		System.out.println("But it's abstract!");
		return null;
	}
	
	/**
	 * @author Smile
	 * @Description set user to his group
	 * @param user
	 * @return updated or not
	 */
	public boolean setGroup(User user) {
		return false;
	}
}
