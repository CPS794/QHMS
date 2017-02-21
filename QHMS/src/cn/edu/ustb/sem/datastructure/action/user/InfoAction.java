/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File InfoAction.java
 * @Time Mar 7, 2016 10:41:01 AM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.user;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.AdminDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class InfoAction extends BaseAction {
	private static final long	serialVersionUID	= -8254148167909442365L;
	private static Logger		logger				= Logger.getLogger(InfoAction.class);

	@Action(value = "password")
	public void modifyPassword() throws IOException {
		String oldPassword = getParameter("oldPassword");
		String newPassword = getParameter("newPassword");
		User user = (User) getSession("CurrentUser");
		logger.debug(user + "\n" + oldPassword + " " + newPassword);
		UserDAO userDAO = null;
		if (user.getType() == UserType.student.getValue()) {
			logger.debug("Student want to modify his password!");
			userDAO = new StudentDAOJdbcImpl();
		}
		user.setPassword(oldPassword);
		user = userDAO.login(user);
		JSONObject json = new JSONObject();
		if (user.isValid()) {
			user.setPassword(newPassword);
			logger.debug(user);
			if (userDAO.update(user)){
				json.element("success", true);
				setSession("CurrentUser", user);
			} else {
				json.element("success", false);
				json.element("message", "密码修改失败！");
			}
		} else {
			json.element("success", false);
			json.element("message", "原密码错误！");
		}
		writeJson(json);
	}
	
	@Action(value = "passwordManage")
	public void modifyUserPassword() throws IOException {
		logger.debug("Modify Password!");
		String userId = getParameter("userId");
		int userType=Integer.parseInt(getParameter("userType"));
		String newPassword = getParameter("newPassword");
		User user = (User) getSession("CurrentUser");
		if (user.getType()==UserType.admin.getValue())
		{
			UserDAO userDAO = null;
			if (userType == UserType.student.getValue()) {
				userDAO = new StudentDAOJdbcImpl();
			}
			else if (userType == UserType.admin.getValue()) {
				userDAO = new AdminDAOJdbcImpl();
			}
			User target=userDAO.findById(userId);
			target.setPassword(newPassword);
			JSONObject json = new JSONObject();
			if (userDAO.update(target)){
				json.element("success", true);
			} else {
				json.element("success", false);
				json.element("message", "密码修改失败！");
			}
			writeJson(json);
		}
		else{
			writeJson(SUCCESS, false);
		}
	}
	
}
