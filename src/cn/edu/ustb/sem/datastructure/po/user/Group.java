/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Group.java
 * @Time May 15, 2016 10:46:18 AM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.user;

import java.util.List;

/**
 * @author Smile
 * @Description
 */
public class Group {
	int id;
	String name;
	int chapterId;
	List<User> members;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the chapterId
	 */
	public int getChapterId() {
		return chapterId;
	}
	/**
	 * @param chapterId the chapterId to set
	 */
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	/**
	 * @return the members
	 */
	public List<User> getMembers() {
		return members;
	}
	/**
	 * @param members the members to set
	 */
	public void setMembers(List<User> members) {
		this.members = members;
	}
}
