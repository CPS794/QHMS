package cn.edu.ustb.sem.datastructure.action.course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ChapterDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.course.impl.ProblemDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.course.Chapter;
import cn.edu.ustb.sem.datastructure.po.course.Problem;
import cn.edu.ustb.sem.datastructure.po.course.ProblemType;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.service.course.ProblemService;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class ProblemAction extends BaseAction {

	private static final long	serialVersionUID	= 4706982910866900235L;
	private static Logger		logger				= Logger.getLogger(ProblemAction.class);
	private int					pid;

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	@Action(value = "problemManage",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/admin/manage/problemManage.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/problem/problemManageStudent.jsp") })
	public String showProblemManagePage() {
		logger.debug("Show Add Problem Page!");
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "addProblemPage",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/admin/problem/adminAddProblem.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/problem/studentAddProblem.jsp") })
	public String showAddProblemPage() {
		logger.debug("Show Add Problem Page!");
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "modifyProblemPage",
			results = {
					@Result(name = "admin",
							location = "/WEB-INF/page/admin/problem/adminModifyProblem.jsp"),
					@Result(name = "student",
							location = "/WEB-INF/page/student/problem/studentModifyProblem.jsp") })
	public String showModifyProblemPage() {
		logger.debug("Show Modify Problem Page!");
		int problemId = Integer.valueOf(getParameter("problemId"));
		setPid(problemId);
		String returnValue[] = { "student", "admin" };
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}

	@Action(value = "listProblems")
	public void getProblemList() throws IOException {
		User user = (User) getSession("CurrentUser");
		List<Problem> problems = null;
		JSONObject json = new JSONObject();
		if (user.getType() == UserType.admin.getValue()) {
			problems = ProblemService.getProblemList();
			json.element(SUCCESS, true);
		} else if (user.getGroup() > 0) {
			problems = ProblemService.getProblemList(user.getGroup());
			json.element(SUCCESS, true);
			Chapter chapter = ChapterDAOJdbcImpl.findById(user.getGroup());
			json.element("status", chapter.getStatus());
		} else {
			json.element(SUCCESS, false);
			json.element("message", "你还没有分组，无权进行此操作");
		}
		JSONArray jsonArray = JSONArray.fromObject(problems);
		json.element("problems", jsonArray);
		writeJson(json);
	}

	@Action(value = "getProblem")
	public void getProblem() throws IOException {
		User user = (User) getSession("CurrentUser");
		int problemId = Integer.valueOf(getParameter("problemId"));
		Problem problem = null;
		JSONObject json = new JSONObject();
		if (user.getType() == UserType.admin.getValue()) {
			problem = ProblemService.getProblem(problemId);
			json.element(SUCCESS, true);
		} else if (user.getGroup() > 0) {
			problem = ProblemService.getProblem(problemId);
			if (problem.getChapterId() == user.getGroup()) {
				json.element(SUCCESS, true);
			} else {
				problem = null;
				json.element(SUCCESS, false);
				json.element("message", "你所在的小组不是这个章节的管理员，无权进行此操作");
			}
		} else {
			json.element(SUCCESS, false);
			json.element("message", "你还没有分组，无权进行此操作");
		}
		json.element("problem", problem);
		writeJson(json);
	}

	@Action(value = "getChapterProblems")
	public void getChapterProblems() throws IOException {
		logger.debug(getParameter("chapterId"));
		int problemId = Integer.valueOf(getParameter("chapterId"));
		List<Problem> problems=ProblemService.getProblemListHomework(problemId);
		JSONObject json = new JSONObject();
		json.element(SUCCESS, true);
		logger.debug(problems);
		JSONArray jsonArray = JSONArray.fromObject(problems);
		json.element("problems", jsonArray);
		writeJson(json);
	}
	
	@Action(value = "addProblemPageInit")
	public void getChapterAndProblemType() throws IOException {
		JSONObject json = new JSONObject();
		json.element(SUCCESS, true);
		json.element("chapters", ProblemService.getChapterList());
		json.element("problemTypes", ProblemService.getProblemTypeList());
		List<ProblemType> types = ProblemService.getProblemTypes();
		JSONArray jsonArray = JSONArray.fromObject(types);
		json.element("types", jsonArray);
		List<Chapter> chapters = ChapterDAOJdbcImpl.findAll();
		jsonArray = JSONArray.fromObject(chapters);
		json.element("object_chapters", jsonArray);
		User user = (User) getSession("CurrentUser");
		if (user.getGroup()>0) {
			json.element("group", user.getGroup());
		}
		writeJson(json);
	}

	@SuppressWarnings("unchecked")
	@Action(value = "addProblem")
	public void addProblem() throws IOException {
		logger.debug(getParameter("problem"));
		JSONObject json = JSONObject.fromObject(getParameter("problem"));
		Map<String, Object> jsonMap = (Map<String, Object>) json;
		Problem problem = new Problem();
		User user = (User) getSession("CurrentUser");
		problem.setAuthor(user.getId());
		problem.setTypeId(Integer.valueOf((String) jsonMap.get("typeId")));
		if (user.getGroup() == 0 && user.getType() == UserType.admin.getValue()) {
			problem.setChapterId(Integer.valueOf((String) jsonMap.get("chapterId")));
		} else {
			problem.setChapterId(user.getGroup());
		}
		problem.setDescription((String) jsonMap.get("description"));
		problem.setOption((String) jsonMap.get("option"));
		problem.setAnswer((String) jsonMap.get("answer"));
		problem.setPoint(Integer.valueOf((String) jsonMap.get("point")));
		logger.debug(problem);
		json = new JSONObject();
		if (ProblemDAOJdbcImpl.insert(problem)) {
			json.element(SUCCESS, true);
		} else {
			json.element(SUCCESS, false);
			json.element("message", "添加题目失败，请检查后重试！");
		}
		writeJson(json);
	}

	@SuppressWarnings("unchecked")
	@Action(value = "modifyProblem")
	public void modifyProblem() throws IOException {
		logger.debug(getParameter("problem"));
		JSONObject json = JSONObject.fromObject(getParameter("problem"));
		Map<String, Object> jsonMap = (Map<String, Object>) json;
		Problem problem = new Problem();
		User user = (User) getSession("CurrentUser");
		problem.setId(Integer.valueOf((String) jsonMap.get("id")));
		problem.setTypeId(Integer.valueOf((String) jsonMap.get("typeId")));
		if (user.getGroup() == 0) {
			problem.setChapterId(Integer.valueOf((String) jsonMap.get("chapterId")));
		} else {
			problem.setChapterId(user.getGroup());
		}
		problem.setDescription((String) jsonMap.get("description"));
		problem.setOption((String) jsonMap.get("option"));
		problem.setAnswer((String) jsonMap.get("answer"));
		problem.setPoint(Integer.valueOf((String) jsonMap.get("point")));
		logger.debug(problem);
		json = new JSONObject();
		if (ProblemDAOJdbcImpl.update(problem)) {
			json.element(SUCCESS, true);
		} else {
			json.element(SUCCESS, false);
			json.element("message", "修改题目失败，请检查后重试！");
		}
		writeJson(json);
	}

	@Action(value = "deleteProblem")
	public void deleteProblem() throws IOException {
		logger.debug("Delete Problem!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			String deleteProblemList = getParameter("deleteIds");
			logger.debug(deleteProblemList);
			String[] problems = deleteProblemList.split(",", 0);
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < problems.length; i++) {
				ids.add(Integer.valueOf(problems[i]));
			}
			ProblemService.deleteProblem(ids);
			writeJson(SUCCESS, true);
		} else if (user.getGroup() > 0) {
			String deleteProblemList = getParameter("deleteIds");
			logger.debug(deleteProblemList);
			String[] problems = deleteProblemList.split(",", 0);
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < problems.length; i++) {
				Problem problem = ProblemDAOJdbcImpl.findByIdWithoutAuthorAndAnswer(Integer.valueOf(problems[i]));
				if (problem.getChapterId() == user.getGroup()) {
					ids.add(Integer.valueOf(problems[i]));
				}
			}
			ProblemService.deleteProblem(ids);
			writeJson(SUCCESS, true);
		} else {
			logger.debug("You are not allowed to delete it!");
		}
	}
	
	@Action(value = "publicHomework")
	public void publicHomework() throws IOException {
		logger.debug("Public Homework!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			writeJson(SUCCESS, false);
		} else if (user.getGroup() > 0) {
			Chapter chapter = ChapterDAOJdbcImpl.findById(user.getGroup());
			chapter.setStatus(1);
			ChapterDAOJdbcImpl.update(chapter);
			writeJson(SUCCESS, true);
		} else {
			logger.debug("You are not allowed to delete it!");
		}
	}
	
}
