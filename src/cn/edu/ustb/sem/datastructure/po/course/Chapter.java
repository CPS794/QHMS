/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Chapter.java
 * @Time May 15, 2016 10:48:57 AM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

/**
 * @author Smile
 * @Description
 */
public class Chapter {
	int		id;
	String	chineseId;
	String	name;
	String	displayName;
	int		status;
	String	displayStatus;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the chineseId
	 */
	public String getChineseId() {
		return chineseId;
	}

	/**
	 * @param chineseId
	 *            the chineseId to set
	 */
	public void setChineseId(String chineseId) {
		this.chineseId = chineseId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		displayName = chineseId + " " + name;
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the displayStatus
	 */
	public String getDisplayStatus() {
		return displayStatus;
	}

	/**
	 * @param displayStatus the displayStatus to set
	 */
	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}
}
