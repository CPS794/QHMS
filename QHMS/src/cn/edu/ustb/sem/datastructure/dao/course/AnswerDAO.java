/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File AnswerDAO.java
 * @Time May 17, 2016 11:34:38 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.course.Answer;

/**
 * @author Smile
 * @Description
 */
public abstract class AnswerDAO {
	public static List<Answer> findAll() {
		return null;
	}

	public static List<Answer> findByChapter(int chapter) {
		return null;
	}

	public static Answer findById(String studentId, int problemId) {
		return null;
	}

	public static boolean insert(Answer answer) {
		return false;
	}

	public static boolean delete(Answer answer) {
		return false;
	}

	public static boolean update(Answer answer) {
		return false;
	}
}
