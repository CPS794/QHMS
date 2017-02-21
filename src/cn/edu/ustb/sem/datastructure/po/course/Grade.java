/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Grade.java
 * @Time Jul 2, 2016 2:52:00 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

/**
 * @author Smile
 * @Description
 */
public class Grade {
	String	studentId;
	int		chapterId;
	double	point;

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the chapterId
	 */
	public int getChapterId() {
		return chapterId;
	}

	/**
	 * @param chapterId
	 *            the chapterId to set
	 */
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	/**
	 * @return the point
	 */
	public double getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(double point) {
		this.point = point;
	}
}
