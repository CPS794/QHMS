/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Evaluate.java
 * @Time May 30, 2016 6:50:08 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

/**
 * @author Smile
 * @Description
 */
public class Evaluation {
	String	studentId;
	int		chapterId;
	int		scorePercentageId;
	String	scoreName;
	int		point;
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
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
	 * @param chapterId the chapterId to set
	 */
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	/**
	 * @return the scorePercentageId
	 */
	public int getScorePercentageId() {
		return scorePercentageId;
	}
	/**
	 * @param scorePercentageId the scorePercentageId to set
	 */
	public void setScorePercentageId(int scorePercentageId) {
		this.scorePercentageId = scorePercentageId;
	}
	/**
	 * @return the scoreName
	 */
	public String getScoreName() {
		return scoreName;
	}
	/**
	 * @param scoreName the scoreName to set
	 */
	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}
	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
}
