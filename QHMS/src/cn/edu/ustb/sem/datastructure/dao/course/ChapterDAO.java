/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ChapterDAO.java
 * @Time May 16, 2016 5:25:07 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.dao.course;

import java.util.List;

import cn.edu.ustb.sem.datastructure.po.course.Chapter;

/**
 * @author Smile
 * @Description
 */
public abstract class ChapterDAO {
	public static List<Chapter> findAll() {
		return null;
	}
	
	public static Chapter findById(int id) {
		return null;
	}
	
	public static boolean update(Chapter chapter) {
		return false;
	}
	
}
