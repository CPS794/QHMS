/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File HomeworkService.java
 * @Time May 26, 2016 7:18:38 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.service.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.impl.AnswerDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Answer;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.ProblemAnswer;
import cn.edu.ustb.sem.datastructure.po.system.ExcelColumn;
import cn.edu.ustb.sem.datastructure.util.AnythingToExcel;
import net.sf.json.JSONArray;

/**
 * @author Smile
 * @Description
 */
public class HomeworkService {
	private static Logger logger = Logger.getLogger(HomeworkService.class);

	public static void answerToExcelByStudent(String fileName, String sheetName, int chapterId) {
		List<ExcelColumn> thead = new ArrayList<>();
		ExcelColumn column = new ExcelColumn();
		column.setId("student_fullname");
		column.setDisplayName("学号姓名");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("problemId");
		column.setDisplayName("题目编号");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("problemAnswer");
		column.setDisplayName("参考答案");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("answer");
		column.setDisplayName("同学回答");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("point");
		column.setDisplayName("得分");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("fullPoint");
		column.setDisplayName("本题满分");
		thead.add(column);
		List<ProblemAnswer> answers = AnswerDAOJdbcImpl.findProblemWrongAnswerByChapter(chapterId);
		List<Map<String, Object>> table = new ArrayList<>();
		for (int i = 0; i < answers.size(); i++) {
			Map<String, Object> answerMap = new HashMap<>();
			answerMap.put("student_fullname", answers.get(i).getStudent_fullname());
			answerMap.put("problemId", answers.get(i).getProblemId());
			answerMap.put("problemAnswer",
					answers.get(i).getProblemAnswer().replace("[\"", "").replace("\"]", "").replace(
							"\",\"", ","));
			answerMap.put("answer",
					answers.get(i).getAnswer().replace("[\"", "").replace("\"]", "").replace(
							"\",\"", ","));
			answerMap.put("point", answers.get(i).getPoint());
			answerMap.put("fullPoint", answers.get(i).getFullPoint());
			table.add(answerMap);
		}
		logger.debug("ProblemAnswer To Excel: " + fileName + " " + sheetName);
		AnythingToExcel.listToExcel(thead, table, fileName, sheetName);
	}

	public static void answerAllToExcelByStudent(String fileName, String sheetName, int chapterId) {
		List<ExcelColumn> thead = new ArrayList<>();
		ExcelColumn column = new ExcelColumn();
		column.setId("problemId");
		column.setDisplayName("题目编号");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("answer");
		column.setDisplayName("同学回答");
		thead.add(column);
		column = new ExcelColumn();
		column.setId("point");
		column.setDisplayName("得分");
		thead.add(column);
		List<Answer> answers = AnswerDAOJdbcImpl.findByChapter(chapterId);
		List<Map<String, Object>> table = new ArrayList<>();
		for (int i = 0; i < answers.size(); i++) {
			Map<String, Object> answerMap = new HashMap<>();
			answerMap.put("problemId", answers.get(i).getProblemId());
			answerMap.put("answer",
					answers.get(i).getAnswer().replace("[\"", "").replace("\"]", "").replace(
							"\",\"", ","));
			answerMap.put("point", answers.get(i).getPoint());
			table.add(answerMap);
		}
		logger.debug("Answer To Excel: " + fileName + " " + sheetName);
		AnythingToExcel.listToExcel(thead, table, fileName, sheetName);
	}
	
	public static void gradeToExcel(String fileName, String sheetName) {
		List<String> thead = new ArrayList<>();
		thead.add("学号");
		thead.add("姓名");
		thead.add("班级");
		thead.add("分组");
		List<Chapter> chapters=GradeService.chaptersWithGrade();
		for (int i = 0; i < chapters.size(); i++) {
			thead.add(chapters.get(i).getChineseId());
		}
		thead.add("平均分");
		JSONArray title=JSONArray.fromObject(thead);
		JSONArray table=JSONArray.fromObject(GradeService.getGrades());
		logger.debug("ProblemAnswer To Excel: " + fileName + " " + sheetName);
		AnythingToExcel.jsonArrayToExcel(title, table, fileName, sheetName);
	}
}
