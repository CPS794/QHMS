/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File LoginAction.java
 * @Time Mar 2, 2016 3:13:17 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.user;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.AdminDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.user.Admin;
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
@Results({
		@Result(name = "studentHomepage", location = "/WEB-INF/page/student/studentHomepage.jsp"),
		@Result(name = "adminHomepage", location = "/WEB-INF/page/admin/adminHomepage.jsp") })
public class LoginAction extends BaseAction {

	private String				returnValue[]		= { "studentHomepage", "adminHomepage" };
	private static Logger		logger				= Logger.getLogger(LoginAction.class);

	private static final long	serialVersionUID	= 3542887665661011722L;

	/**
	 * @author Smile
	 * @Description User login. Return a json string to the browser;
	 * @throws IOException
	 */
	@Action(value = "login")
	public void login() throws IOException {
		String userType = getParameter("userType");
		String userName = getParameter("userName");
		String password = getParameter("password");
		User user = null;
		UserDAO userDAO = null;
		logger.debug("Try to login:" + userType + " " + userName + " " + password);
		// student
		if (Integer.parseInt(userType) == UserType.student.getValue()) {
			logger.debug("A student!");
			user = new Student();
			userDAO = new StudentDAOJdbcImpl();
		}
		// admin
		else if (Integer.parseInt(userType) == UserType.admin.getValue()) {
			logger.debug("An admin!");
			user = new Admin();
			userDAO = new AdminDAOJdbcImpl();
		}
		user.setId(userName);
		user.setPassword(password);
		user = userDAO.login(user);
		logger.debug(user.toString());
		JSONObject json = new JSONObject();
		if (user.isValid()) {
			setSession("CurrentUser", user);
			json.element("success", true);
			json.element("location", "home.action");
		} else {
			json.element("success", false);
			json.element("message", "用户名或密码错误");
		}
		writeJson(json);
	}

	/**
	 * @author Smile
	 * @Description For a user with session, lead him to his homepage
	 * @return The corresponding home page of that type of user
	 */
	@Action(value = "home")
	public String home() {
		User user = (User) getSession("CurrentUser");
		return returnValue[user.getType()];
	}
	
	@Action(value = "logout")
	public void logout() throws IOException {
		User user = (User) getSession("CurrentUser");
		logger.info("Logout:" + user.toString());
		clearSession();
		writeJson(SUCCESS, true);
	}
}
