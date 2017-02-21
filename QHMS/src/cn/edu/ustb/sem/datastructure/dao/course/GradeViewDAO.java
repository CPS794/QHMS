/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GradeViewDAO.java
 * @Time Jul 2, 2016 2:50:48 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.course.Grade;

/**
 * @author Smile
 * @Description
 */
public abstract class GradeViewDAO {
	public static List<Grade> findAll() {
		return null;
	}

	public static List<Grade> findByStudent(String student) {
		return null;
	}

	public static List<Grade> findByChapter(int chapter) {
		return null;
	}

	public static Grade findByStudentAndChapter(String student, int chapter) {
		return null;
	}
}
