/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemDAO.java
 * @Time May 15, 2016 9:07:25 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.course.Problem;

/**
 * @author Smile
 * @Description
 */
public abstract class ProblemDAO {
	public static List<Problem> findAll() {
		return null;
	}

	public static List<Problem> findByChapter(int chapter) {
		return null;
	}

	public static Problem findById(int id) {
		return null;
	}

	public static boolean insert(Problem problem) {
		return false;
	}

	public static boolean delete(Problem problem) {
		return false;
	}

	public static boolean update(Problem problem) {
		return false;
	}

}
