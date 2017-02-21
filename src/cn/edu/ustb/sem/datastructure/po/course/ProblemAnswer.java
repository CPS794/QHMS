/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemAnswer.java
 * @Time May 20, 2016 6:35:07 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.course;

import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;

/**
 * @author Smile
 * @Description
 */
public class ProblemAnswer extends Answer {
	int				typeId;
	String			description;
	String			problemAnswer;
	String			option;
	int				fullPoint;
	int				chapterId;
	private String	student_fullname;
	private String	teacher_fullname;

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the problemAnswer
	 */
	public String getProblemAnswer() {
		return problemAnswer;
	}

	/**
	 * @param problemAnswer
	 *            the problemAnswer to set
	 */
	public void setProblemAnswer(String problemAnswer) {
		this.problemAnswer = problemAnswer;
	}

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * @return the fullPoint
	 */
	public int getFullPoint() {
		return fullPoint;
	}

	/**
	 * @param fullPoint
	 *            the fullPoint to set
	 */
	public void setFullPoint(int fullPoint) {
		this.fullPoint = fullPoint;
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
	 * @return the teacher_fullname
	 */
	public String getTeacher_fullname() {
		if (teacherId != null && !teacherId.equals("")) {
			if (teacher_fullname == null) {
				UserDAO userDAO = new StudentDAOJdbcImpl();
				String user = userDAO.findById(teacherId).getName();
				if (user != null) {
					teacher_fullname = "[" + teacherId + "]" + user;
				} else {
					teacher_fullname = "[Admin]" + "管理员";
				}
			}
		} else {
			if (teacher_fullname == null) {
				teacher_fullname = "";
			}
		}
		return teacher_fullname;
	}

	/**
	 * @param teacher_fullname
	 *            the teacher_fullname to set
	 */
	public void setTeacher_fullname(String teacher_fullname) {
		this.teacher_fullname = teacher_fullname;
	}

	/**
	 * @return the student_fullname
	 */
	public String getStudent_fullname() {
		if (studentId != null && !studentId.equals("")) {
			if (student_fullname == null) {
				UserDAO userDAO = new StudentDAOJdbcImpl();
				String user = userDAO.findById(studentId).getName();
				if (user != null) {
					student_fullname = "[" + studentId + "]" + user;
				} else {
					student_fullname = "[NULL]" + "查无此人";
				}
			}
		} else {
			if (student_fullname == null) {
				student_fullname = "";
			}
		}
		return student_fullname;
	}

	/**
	 * @param student_fullname the student_fullname to set
	 */
	public void setStudent_fullname(String student_fullname) {
		this.student_fullname = student_fullname;
	}
}
