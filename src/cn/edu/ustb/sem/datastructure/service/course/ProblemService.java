/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ProblemService.java
 * @Time May 16, 2016 6:57:24 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.service.course;

import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.course.impl.ChapterDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ProblemDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ProblemTypeDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.Problem;
import cn.edu.ustb.sem.datastructure.po.course.ProblemType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
public class ProblemService {
	private static Logger logger = Logger.getLogger(ProblemService.class);

	public static JSONArray getChapterList() {
		List<Chapter> chapters = ChapterDAOJdbcImpl.findAll();
		JSONArray chaptersArray = new JSONArray();
		for (int i = 0; i < chapters.size(); i++) {
			JSONObject chapter = new JSONObject();
			chapter.element("key", chapters.get(i).getId());
			chapter.element("value", chapters.get(i).getDisplayName());
			chaptersArray.add(chapter);
		}
		logger.debug(chaptersArray);
		return chaptersArray;
	}

	public static JSONArray getProblemTypeList() {
		List<ProblemType> problemTypes = ProblemTypeDAOJdbcImpl.findAll();
		JSONArray problemTypesArray = new JSONArray();
		for (int i = 0; i < problemTypes.size(); i++) {
			JSONObject problemType = new JSONObject();
			problemType.element("key", problemTypes.get(i).getId());
			problemType.element("value", problemTypes.get(i).getName());
			problemTypesArray.add(problemType);
		}
		logger.debug(problemTypesArray);
		return problemTypesArray;
	}

	public static List<ProblemType> getProblemTypes() {
		return ProblemTypeDAOJdbcImpl.findAll();
	}

	public static List<Problem> getProblemList() {
		return ProblemDAOJdbcImpl.findAllWithoutAnswer();
	}

	public static List<Problem> getProblemList(int chapterId) {
		return ProblemDAOJdbcImpl.findByChapterWithoutAnswer(chapterId);
	}

	public static List<Problem> getProblemListAndAnswer(int chapterId) {
		return ProblemDAOJdbcImpl.findByChapter(chapterId);
	}
	
	public static List<Problem> getProblemListHomework(int chapterId) {
		List<Problem> problems = getProblemList(chapterId);
		for (int i = 0; i < problems.size(); i++) {
			problems.get(i).setAuthor("");
			problems.get(i).setAuthor_fullname("");
		}
		return problems;
	}

	public static Problem getProblem(int id) {
		return ProblemDAOJdbcImpl.findById(id);
	}

	public static int deleteProblem(List<Integer> ids) {
		int count = 0;
		Problem problem = new Problem();
		for (int i = 0; i < ids.size(); i++) {
			problem.setId(ids.get(i));
			if (ProblemDAOJdbcImpl.delete(problem)) {
				count++;
			}
		}
		return count;
	}

}
