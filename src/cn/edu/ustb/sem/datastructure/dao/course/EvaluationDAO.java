/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File EvaluationDAO.java
 * @Time May 30, 2016 7:43:46 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.course.Evaluation;

/**
 * @author Smile
 * @Description
 */
public abstract class EvaluationDAO {
	public static List<Evaluation> findAll() {
		return null;
	}

	public static List<Evaluation> findByStudent(String studentId) {
		return null;
	}

	public static Evaluation findById(String studentId, int chapterId) {
		return null;
	}

	public static boolean insert(Evaluation evaluation) {
		return false;
	}

	public static boolean delete(Evaluation evaluation) {
		return false;
	}

	public static boolean update(Evaluation evaluation) {
		return false;
	}

}
