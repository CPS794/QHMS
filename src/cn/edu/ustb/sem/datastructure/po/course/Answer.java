/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Answer.java
 * @Time May 17, 2016 11:27:50 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

/**
 * @author Smile
 * @Description
 */
public class Answer {
	String studentId;
	int problemId;
	String answer;
	int point;
	String teacherId;
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
	 * @return the problemId
	 */
	public int getProblemId() {
		return problemId;
	}
	/**
	 * @param problemId the problemId to set
	 */
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
	/**
	 * @return the teacherId
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Override
	public String toString() {
		return "Answer [studentId="+studentId+", problemId="+problemId+", answer="+answer+", point="+point+", teacherId="+teacherId + "]";
	}
}
