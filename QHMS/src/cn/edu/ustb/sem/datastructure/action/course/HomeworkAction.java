/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File HomeworkAction.java
 * @Time May 17, 2016 6:34:37 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.course;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.course.impl.AnswerDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ChapterDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ProblemTypeDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Answer;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.Problem;
import cn.edu.ustb.sem.datastructure.po.course.ProblemAnswer;
import cn.edu.ustb.sem.datastructure.po.course.ProblemType;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.service.course.HomeworkService;
import cn.edu.ustb.sem.datastructure.service.course.ProblemService;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class HomeworkAction extends BaseAction {
	private static final long		serialVersionUID	= -6749580427623500968L;
	private static Logger			logger				= Logger.getLogger(HomeworkAction.class);

	private int						cid;
	private String					uid;
	private static ServletContext	servletContext		= ServletActionContext.getServletContext();
	private static String			webContentRoot		= servletContext.getRealPath("/");
	private InputStream				inputStream;
	private String					fileName;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Action(value = "doHomework",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/student/homework/homeworkDoing.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/homework/homeworkDoing.jsp") })
	public String showHomeworkPage() {
		logger.debug("Show Homework Page!");
		setCid(Integer.valueOf(getParameter("chapter")));
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "remarkHomework",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/student/homework/remarkTotalview.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/homework/remarkTotalview.jsp") })
	public String showRemarkPage() {
		logger.debug("Show Remark Page!");
		User user = (User) getSession("CurrentUser");
		setCid(user.getGroup());
		String returnValue[] = { "student", "admin" };
		return returnValue[user.getType()];
	}

	@Action(value = "showAnswer",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/student/homework/studentAnswer.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/homework/homeworkAnswer.jsp") })
	public String showAnswerPage() {
		logger.debug("Show Homework Page!");
		setCid(Integer.valueOf(getParameter("chapter")));
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			setUid(getParameter("student"));
		}
		return returnValue[user.getType()];
	}

	@Action(value = "myAnswer",
			results = { @Result(name = "admin",
					location = "/WEB-INF/page/student/homework/myAnswer.jsp"),
			@Result(name = "student", location = "/WEB-INF/page/student/homework/myAnswer.jsp") })
	public String myAnswerPage() {
		logger.debug("Show Homework Page!");
		setCid(Integer.valueOf(getParameter("chapter")));
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "downloadAnswer",
			results = { @Result(name = "success", type = "stream",
					params = { "contentType", "application/octet-stream", "inputName",
							"inputStream", "bufferSize", "1024", "contentDisposition",
							"filename=\"${fileName}\"" }) })
	public String downloadAnswer() throws Exception {
		User user = (User) getSession("CurrentUser");
		int chapterId = user.getGroup();
		Chapter chapter = ChapterDAOJdbcImpl.findById(chapterId);
		String pathToFile = webContentRoot + "WebRoot/res/excel";
		Date date = new Date(System.currentTimeMillis());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = pathToFile + File.separator + "Chapter" + chapterId + "_"
				+ dateFormat.format(date) + ".xls";
		setFileName("Chapter" + chapterId + "_" + dateFormat.format(date) + ".xls");
		String sheetName = chapter.getDisplayName() + " 答题情况";
		HomeworkService.answerToExcelByStudent(fileName, sheetName, chapterId);
		try {
			inputStream = new FileInputStream(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "downloadGrade",
			results = { @Result(name = "success", type = "stream",
					params = { "contentType", "application/octet-stream", "inputName",
							"inputStream", "bufferSize", "1024", "contentDisposition",
							"filename=\"${fileName}\"" }) })
	public String downloadGrade() throws Exception {
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			String pathToFile = webContentRoot + "WebRoot/res/excel";
			Date date = new Date(System.currentTimeMillis());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = pathToFile + File.separator + "Grade_"
					+ dateFormat.format(date) + ".xls";
			setFileName("Grade_" + dateFormat.format(date) + ".xls");
			String sheetName = "所有同学各章节作业得分";
			HomeworkService.gradeToExcel(fileName, sheetName);
			try {
				inputStream = new FileInputStream(new File(fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	@Action(value = "homeworkTotalView")
	public void homeworkTotalView() throws IOException {
		User user = (User) getSession("CurrentUser");
		List<Chapter> chapters = ChapterDAOJdbcImpl.findAll();
		int answerCount = 0;
		for (int i = 0; i < chapters.size(); i++) {
			answerCount = AnswerDAOJdbcImpl.countByStudentAndChapter(chapters.get(i).getId(),
					user.getId());
			if (chapters.get(i).getStatus() == 0) {
				chapters.get(i).setDisplayStatus("出题中");
			} else if (chapters.get(i).getStatus() == 1) {
				if (answerCount == 0) {
					chapters.get(i).setDisplayStatus("作业待完成");
				} else {
					chapters.get(i).setDisplayStatus("作业批改中");
				}
			} else if (chapters.get(i).getStatus() == 2) {
				if (answerCount == 0) {
					chapters.get(i).setDisplayStatus("作业待完成");
				} else {
					int answerNoRemark = AnswerDAOJdbcImpl.countNotFinishedByStudentAndChapter(
							chapters.get(i).getId(), user.getId());
					logger.debug("Number of not finished: " + answerNoRemark);
					if (answerNoRemark > 0) {
						chapters.get(i).setDisplayStatus("作业批改中");
					} else {
						chapters.get(i).setDisplayStatus("作业已批改");
					}
				}
			} else {
				chapters.get(i).setDisplayStatus("已完成");
			}
		}
		JSONObject json = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(chapters);
		json.element(SUCCESS, true);
		json.element("chapters", jsonArray);
		json.element("myChapter", user.getGroup());
		writeJson(json);
	}

	@Action(value = "submitHomework")
	public void submitHomework() throws IOException {
		User user = (User) getSession("CurrentUser");
		int chapterId = Integer.valueOf((String) getParameter("chapter"));
		// logger.debug(chapterId);
		JSONObject json = new JSONObject();
		if (user.getGroup() != chapterId) {
			JSONArray jsonArray = JSONArray.fromObject(getParameter("answers"));
			// logger.debug(jsonArray);
			List<Problem> problems = ProblemService.getProblemListAndAnswer(chapterId);
			List<ProblemType> problemTypes = ProblemTypeDAOJdbcImpl.findAll();
			Map<Integer, ProblemType> problemTypeMap = new HashMap<>();
			for (int i = 0; i < problemTypes.size(); i++) {
				problemTypeMap.put(problemTypes.get(i).getId(), problemTypes.get(i));
			}
			Map<Integer, Problem> problemMap = new HashMap<>();
			for (int i = 0; i < problems.size(); i++) {
				problemMap.put(problems.get(i).getId(), problems.get(i));
			}
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject answerJson = (JSONObject) jsonArray.getJSONObject(i);
				String ori = answerJson.toString();
				// logger.debug(ori);
				int pos = ori.indexOf("\"problemId\":");
				String problemIdString = ori.substring(pos + 12, ori.length() - 1);
				// logger.debug(problemIdString);
				Answer answer = new Answer();
				answer.setProblemId(Integer.valueOf(problemIdString));
				String anString = ori.substring(10, pos - 1);
				// logger.debug(anString);
				answer.setAnswer(anString);
				answer.setStudentId(user.getId());
				Problem problem = problemMap.get(answer.getProblemId());
				if (problem != null) {
					// logger.debug(problem);
					if (problemTypeMap.get(problem.getTypeId()).getJudge() == 0) {
						// logger.debug(answer.getAnswer() + " == " +
						// problem.getAnswer());
						if (answer.getAnswer().equals(problem.getAnswer())) {
							// logger.debug("Point=" + problem.getPoint());
							answer.setPoint(problem.getPoint());
						} else {
							answer.setPoint(0);
						}
					}
					AnswerDAOJdbcImpl.insert(answer);
				}
			}
			json.element(SUCCESS, true);
		} else {
			json.element(SUCCESS, false);
			json.element("message", "请不要提交自己组出的题目！");
		}
		writeJson(json);
	}

	@Action(value = "listAnswers")
	public void remarkTotalView() throws IOException {
		User user = (User) getSession("CurrentUser");
		int chapterId = user.getGroup();
		int status = ChapterDAOJdbcImpl.findById(chapterId).getStatus();
		List<ProblemAnswer> answers = AnswerDAOJdbcImpl.findProblemAnswerByChapter(chapterId);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(answers);
		json.element(SUCCESS, true);
		json.element("answers", jsonArray);
		json.element("chapter", user.getGroup());
		json.element("status", status);
		writeJson(json);
	}

	@Action(value = "submitRemark")
	public void remarkSubmit() throws IOException {
		User user = (User) getSession("CurrentUser");
		String studentId = getParameter("studentId");
		int problemId = Integer.valueOf(getParameter("problemId"));
		int point = Integer.valueOf(getParameter("point"));
		Answer answer = AnswerDAOJdbcImpl.findById(studentId, problemId);
		answer.setTeacherId(user.getId());
		answer.setPoint(point);
		if (AnswerDAOJdbcImpl.update(answer)) {
			writeJson(SUCCESS, true);
		} else {
			writeJson(SUCCESS, false);
		}
	}

	@Action(value = "publicAnswer")
	public void publicAnswer() throws IOException {
		logger.debug("Public Answer!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			writeJson(SUCCESS, false);
		} else if (user.getGroup() > 0) {
			Chapter chapter = ChapterDAOJdbcImpl.findById(user.getGroup());
			chapter.setStatus(2);
			ChapterDAOJdbcImpl.update(chapter);
			writeJson(SUCCESS, true);
		} else {
			logger.debug("You are not allowed to change it!");
		}
	}

	@Action(value = "getChapterAnswers")
	public void getChapterAnswers() throws IOException {
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			String studentId = getParameter("studentId");
			UserDAO studentDAO = new StudentDAOJdbcImpl();
			user = studentDAO.findById(studentId);
			logger.debug("studentId=" + studentId);
		}
		int chapterId = Integer.valueOf(getParameter("chapterId"));
		logger.debug("chapterId=" + chapterId);
		Chapter chapter = ChapterDAOJdbcImpl.findById(chapterId);
		int answerCount = AnswerDAOJdbcImpl.countByStudentAndChapter(chapterId, user.getId());
		int answerNoRemark =
				AnswerDAOJdbcImpl.countNotFinishedByStudentAndChapter(chapterId, user.getId());
		JSONObject json = new JSONObject();
		if (chapter.getStatus() >= 2 && answerCount > 0 && answerNoRemark == 0) {
			List<ProblemAnswer> answers = AnswerDAOJdbcImpl.findAllProblemAnswerByStudentAndChapter(
					chapterId, user.getId());
			int totalGrade = 0;
			for (int i = 0; i < answers.size(); i++) {
				answers.get(i).setTeacherId("");
				answers.get(i).setTeacher_fullname("");
				totalGrade += answers.get(i).getPoint();
			}
			JSONArray jsonArray = JSONArray.fromObject(answers);
			json.element(SUCCESS, true);
			json.element("answers", jsonArray);
			json.element("chapter", chapter);
			json.element("grade", totalGrade);
			json.element("name", user.getName());
		} else {
			json.element(SUCCESS, false);
			json.element("message", "还没有完成该章节！");
		}
		writeJson(json);
	}

	@Action(value = "getMyChapterAnswers")
	public void getMyChapterAnswers() throws IOException {
		User user = (User) getSession("CurrentUser");
		int chapterId = Integer.valueOf(getParameter("chapterId"));
		List<ProblemAnswer> answers =
				AnswerDAOJdbcImpl.findAllProblemAnswerByStudentAndChapter(chapterId, user.getId());
		int totalGrade = 0;
		for (int i = 0; i < answers.size(); i++) {
			answers.get(i).setTeacherId("");
			answers.get(i).setTeacher_fullname("");
			answers.get(i).setProblemAnswer("");
			answers.get(i).setPoint(0);
		}
		Chapter chapter = ChapterDAOJdbcImpl.findById(chapterId);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(answers);
		json.element(SUCCESS, true);
		json.element("answers", jsonArray);
		json.element("chapter", chapter);
		json.element("grade", totalGrade);
		writeJson(json);
	}
}
