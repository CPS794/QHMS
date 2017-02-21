/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GradeService.java
 * @Time Jul 2, 2016 3:33:52 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.service.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.impl.ChapterDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.GradeViewDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.Grade;
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;

/**
 * @author Smile
 * @Description
 */
public class GradeService {
	private static Logger logger = Logger.getLogger(GradeService.class);

	public static List<Chapter> chaptersWithGrade() {
		List<Chapter> chapters = ChapterDAOJdbcImpl.findAll();
		for (int i = chapters.size() - 1; i >= 0; i--) {
			List<Grade> gradeList = GradeViewDAOJdbcImpl.findByChapter(chapters.get(i).getId());
			if (gradeList == null || gradeList.size() <= 0) {
				chapters.remove(i);
			}
		}
		return chapters;
	}

	public static String getGrades() {
		String gradeArray = "";
		gradeArray += "[";
		List<Chapter> chapters = chaptersWithGrade();
		logger.debug("Chapters with grade: " + chapters);
		int chapterNumber = chapters.size();
		int studentNumber = StudentDAOJdbcImpl.findAll().size();
		Double[][] grades = new Double[studentNumber][chapterNumber];
		Map<Integer, Integer> chapterPosition = new HashMap<>();
		for (int i = 0; i < chapters.size(); i++) {
			chapterPosition.put(chapters.get(i).getId(), i);
		}

		List<User> students = StudentDAOJdbcImpl.findAll();
		for (int i = 0; i < students.size(); i++) {
			if (i > 0) {
				gradeArray += ",";
			}
			gradeArray += "[";
			Student student = (Student) students.get(i);
			gradeArray += "\"" + student.getId() + "\"";
			gradeArray += ",";
			gradeArray += "\"" + student.getName() + "\"";
			gradeArray += ",";
			gradeArray += "\"" + student.getStudentClass() + "\"";
			gradeArray += ",";
			gradeArray += "\"" + student.getGroup() + "\"";
			List<Grade> studentGrade = GradeViewDAOJdbcImpl.findByStudent(student.getId());
			for (int j = 0; j < studentGrade.size(); j++) {
				grades[i][chapterPosition.get(studentGrade.get(j).getChapterId())] =
						studentGrade.get(j).getPoint();
			}
			double totalGrade=0;
			int count=0;
			for (int j = 0; j < chapterNumber; j++) {
				gradeArray += ",";
				if (grades[i][j] == null) {
					if (chapters.get(j).getId() == student.getGroup()) {
						gradeArray += "\"出题组\"";
					} else {
						gradeArray += "0";
						count++;
					}
				} else {
					gradeArray += grades[i][j];
					totalGrade+=grades[i][j];
					count++;
				}
			}
			gradeArray += ",";
			gradeArray += totalGrade/count;
			gradeArray += "]";
		}
		gradeArray += "]";
		logger.debug(gradeArray);
		return gradeArray;
	}

}
