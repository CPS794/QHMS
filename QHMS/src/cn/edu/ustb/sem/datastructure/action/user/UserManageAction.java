/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File UserManageAction.java
 * @Time May 6, 2016 1:21:09 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.user;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.AdminDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.GroupDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.user.Admin;
import cn.edu.ustb.sem.datastructure.po.user.Group;
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.service.course.ProblemService;
import cn.edu.ustb.sem.datastructure.service.user.UserService;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class UserManageAction extends BaseAction {
	private static final long	serialVersionUID	= 8337985420049786376L;
	private static Logger		logger				= Logger.getLogger(UserManageAction.class);

	@Action(value = "adminManage", results = { @Result(name = "adminManage",
			location = "/WEB-INF/page/admin/manage/adminManage.jsp") })
	public String adminManage() {
		logger.debug("This is AdminManagePage!");
		return "adminManage";
	}

	@Action(value = "studentManage", results = { @Result(name = "studentManage",
			location = "/WEB-INF/page/admin/manage/studentManage.jsp") })
	public String studentManage() {
		logger.debug("This is StudentManagePage!");
		return "studentManage";
	}

	@Action(value = "viewMyGroup", results = {
			@Result(name = "myGroup", location = "/WEB-INF/page/student/group/myGroup.jsp") })
	public String viewMyGroup() {
		logger.debug("This is StudentGroupMemberPage!");
		return "myGroup";
	}

	@Action(value = "adminList")
	public void getAdminList() throws IOException {
		logger.debug("Admin List!");
		List<User> users = UserService.getAdminList();
		JSONArray jsonArray = JSONArray.fromObject(users);
		JSONObject userList = new JSONObject();
		userList.element(SUCCESS, true);
		userList.element("users", jsonArray);
		writeJson(userList);
	}

	@Action(value = "studentList")
	public void getStudentList() throws IOException {
		logger.debug("Student List!");
		List<User> users = UserService.getStudentList();
		JSONArray jsonArray = JSONArray.fromObject(users);
		JSONObject userList = new JSONObject();
		userList.element(SUCCESS, true);
		userList.element("users", jsonArray);
		userList.element("chapters", ProblemService.getChapterList());
		writeJson(userList);
	}

	@Action(value = "addUser")
	public void addUser() throws IOException {
		logger.debug("Add User!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			User addUser = null;
			UserDAO userDAO = null;
			int userType = Integer.parseInt(getParameter("userType"));
			if (userType == UserType.student.getValue()) {
				addUser = new Student();
				userDAO = new StudentDAOJdbcImpl();
			} else if (userType == UserType.admin.getValue()) {
				addUser = new Admin();
				userDAO = new AdminDAOJdbcImpl();
			}
			addUser.setId(getParameter("userId"));
			addUser.setName(getParameter("userName"));
			addUser.setPassword(getParameter("password"));
			addUser.setType(userType);
			if (userType == UserType.student.getValue()) {
				addUser.setClass(getParameter("studentClass"));
			}
			JSONObject json = new JSONObject();
			if (userDAO.insert(addUser)) {
				json.element("success", true);
			} else {
				json.element("success", false);
				json.element("message", "用户添加失败！");
			}
			writeJson(json);
		}
	}

	@Action(value = "deleteUser")
	public void deleteUser() throws IOException {
		logger.debug("Delete User!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			String deleteUserList = getParameter("deleteIds");
			String[] users = deleteUserList.split(",", 0);

			User deleteUser = null;
			UserDAO userDAO = null;
			int userType = Integer.parseInt(getParameter("userType"));
			if (userType == UserType.student.getValue()) {
				deleteUser = new Student();
				userDAO = new StudentDAOJdbcImpl();
			} else if (userType == UserType.admin.getValue()) {
				deleteUser = new Admin();
				userDAO = new AdminDAOJdbcImpl();
			}
			for (int i = 0; i < users.length; i++) {
				deleteUser.setId(users[i]);
				userDAO.delete(deleteUser);
			}
			writeJson(SUCCESS, true);
		} else {
			logger.debug("You are not allowed to delete it!");
		}
	}

	@Action(value = "modifyUser")
	public void modifyUser() throws IOException {
		logger.debug("Modify User!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			User modifyUser = null;
			UserDAO userDAO = null;
			int userType = Integer.parseInt(getParameter("userType"));
			if (userType == UserType.student.getValue()) {
				modifyUser = new Student();
				userDAO = new StudentDAOJdbcImpl();
			} else if (userType == UserType.admin.getValue()) {
				modifyUser = new Admin();
				userDAO = new AdminDAOJdbcImpl();
			}
			modifyUser.setId(getParameter("userId"));
			modifyUser.setName(getParameter("userName"));
			modifyUser.setType(userType);
			if (userType == UserType.student.getValue()) {
				modifyUser.setClass(getParameter("studentClass"));
			}
			JSONObject json = new JSONObject();
			if (userDAO.updateWithoutPassword(modifyUser)) {
				json.element("success", true);
			} else {
				json.element("success", false);
				json.element("message", "用户修改失败！");
			}
			writeJson(json);
		}
	}

	@Action(value = "setGroup")
	public void setGroup() throws IOException {
		logger.debug("Modify User!");
		User user = (User) getSession("CurrentUser");
		if (user.getType() == UserType.admin.getValue()) {
			String studentIds = getParameter("studentIds");
			String[] students = studentIds.split(",", 0);
			UserDAO userDAO = new StudentDAOJdbcImpl();
			int group = Integer.valueOf(getParameter("group"));
			User student = new Student();
			student.setGroup(group);
			for (int i = 0; i < students.length; i++) {
				student.setId(students[i]);
				userDAO.setGroup(student);
			}
			writeJson(SUCCESS, true);
		}
	}

	@Action(value = "getGroupMember")
	public void getGroupMember() throws IOException {
		logger.debug("Get Group Member!");
		User user = (User) getSession("CurrentUser");
		JSONObject json = new JSONObject();
		if (user.getGroup() > 0) {
			Group group = GroupDAOJdbcImpl.getMembers(user.getGroup());
			JSONArray jsonArray = JSONArray.fromObject(group.getMembers());
			json.element(SUCCESS, true);
			json.element("users", jsonArray);
		} else {
			json.element(SUCCESS, false);
			json.element("message", "你还没有分组呢！");
		}
		writeJson(json);
	}
}
