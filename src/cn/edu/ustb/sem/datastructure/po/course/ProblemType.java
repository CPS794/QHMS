/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemType.java
 * @Time May 16, 2016 7:01:16 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

/**
 * @author Smile
 * @Description
 */
public class ProblemType {
	int		id;
	String	name;
	int		point;
	int		judge;

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
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the judge
	 */
	public int getJudge() {
		return judge;
	}

	/**
	 * @param judge the judge to set
	 */
	public void setJudge(int judge) {
		this.judge = judge;
	}

}
