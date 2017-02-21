/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File EvaluateAction.java
 * @Time May 30, 2016 6:51:35 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.course;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.course.impl.AnswerDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ChapterDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.EvaluationDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.Evaluation;
import cn.edu.ustb.sem.datastructure.po.user.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class EvaluationAction extends BaseAction {

	private static final long	serialVersionUID	= 1904722116445522786L;
	private static Logger		logger				= Logger.getLogger(EvaluationAction.class);

	@Action(value = "evaluationManage",
			results = { @Result(name = "admin",
					location = "/WEB-INF/page/student/evaluation/evaluationTotalView.jsp"),
			@Result(name = "student",
					location = "/WEB-INF/page/student/evaluation/evaluationTotalView.jsp") })
	public String showProblemManagePage() {
		logger.debug("Show Evaluation Page!");
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "evaluationTotalView")
	public void evaluationTotalView() throws IOException {
		User user = (User) getSession("CurrentUser");
		List<Chapter> chapters = ChapterDAOJdbcImpl.findAll();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = null;
		int answerCount = 0;
		for (int i = 0; i < chapters.size(); i++) {
			answerCount = AnswerDAOJdbcImpl.countByStudentAndChapter(chapters.get(i).getId(),
					user.getId());
			if (chapters.get(i).getStatus() < 2) {
				chapters.get(i).setDisplayStatus("未完成");
			} else if (chapters.get(i).getStatus() == 2) {
				if (answerCount == 0) {
					chapters.get(i).setDisplayStatus("未完成");
				} else {
					int answerNoRemark = AnswerDAOJdbcImpl.countNotFinishedByStudentAndChapter(
							chapters.get(i).getId(), user.getId());
					logger.debug("Number of not finished: " + answerNoRemark);
					if (answerNoRemark > 0) {
						chapters.get(i).setDisplayStatus("未完成");
					} else {
						List<Evaluation> evaluations =
								EvaluationDAOJdbcImpl.findByStudentAndChapter(user.getId(),
										chapters.get(i).getId());
						if (evaluations.size() > 0) {
							chapters.get(i).setDisplayStatus("已评价");
						} else {
							evaluations = EvaluationDAOJdbcImpl.findNotFinished();
							chapters.get(i).setDisplayStatus("未评价");
						}
						jsonArray = JSONArray.fromObject(evaluations);
						json.element("chapter_" + chapters.get(i).getId(), jsonArray);
					}
				}
			} else {
				chapters.get(i).setDisplayStatus("已评价");
			}
		}
		jsonArray = JSONArray.fromObject(chapters);
		json.element(SUCCESS, true);
		json.element("chapters", jsonArray);
		json.element("myChapter", user.getGroup());
		writeJson(json);
	}
	
	@Action(value = "evaluationSubmit")
	public void evaluationSubmit() throws IOException {
		logger.debug("Evaluation Submit!");
		User user = (User) getSession("CurrentUser");
		String evaluationString = getParameter("evaluation");
		logger.debug(evaluationString);
		int chapter = Integer.valueOf(getParameter("chapterId"));
		String[] evaluations = evaluationString.split(",", 0);
		for (int i = 0; i < evaluations.length; i++) {
			if (evaluations[i].length()>0)
			{
				Evaluation evaluation = new Evaluation();
				evaluation.setChapterId(chapter);
				evaluation.setScorePercentageId(i);
				evaluation.setPoint(Integer.valueOf(evaluations[i]));
				evaluation.setStudentId(user.getId());
				EvaluationDAOJdbcImpl.insert(evaluation);
			}
		}
		writeJson(SUCCESS, true);
	}
}
