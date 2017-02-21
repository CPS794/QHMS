/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File UserService.java
 * @Time May 11, 2016 2:12:47 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.service.user;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.user.UserDAO;
import cn.edu.ustb.sem.datastructure.dao.user.impl.AdminDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.user.Admin;
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;
import cn.edu.ustb.sem.datastructure.util.Encoder;
import cn.edu.ustb.sem.datastructure.util.ExcelToAnything;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;

/**
 * @author Smile
 * @Description
 */
public class UserService {
	private static Logger logger = Logger.getLogger(UserService.class);

	public static List<User> getAdminList() {
		return AdminDAOJdbcImpl.findAll();
	}

	public static List<User> getStudentList() {
		return StudentDAOJdbcImpl.findAll();
	}

	public static int addAdminFromExcel(String fileName, Map<String, String> columnMap,
			int userType) {
		int count = 0;
		List<Map<String, Object>> admins = ExcelToAnything.excelToList_AllString(fileName);
		User addUser = null;
		UserDAO userDAO = null;
		if (userType == UserType.student.getValue()) {
			addUser = new Student();
			userDAO = new StudentDAOJdbcImpl();
		} else if (userType == UserType.admin.getValue()) {
			addUser = new Admin();
			userDAO = new AdminDAOJdbcImpl();
		}
		for (int i = 0; i < admins.size(); i++) {
			Map<String, Object> admin = admins.get(i);
			addUser.setId(String.valueOf(admin.get(columnMap.get("id"))));
			addUser.setName(String.valueOf(admin.get(columnMap.get("name"))));
			addUser.setPassword(String.valueOf(admin.get(columnMap.get("password"))));
			addUser.setType(userType);
			if (userDAO.insert(addUser)) {
				count++;
			}
		}
		return count;
	}

	public static int addStudentFromExcel(String fileName, Map<String, String> columnMap,
			int userType) {
		int count = 0;
		List<Map<String, Object>> students = ExcelToAnything.excelToList_AllString(fileName);
		User addUser = null;
		UserDAO userDAO = null;
		if (userType == UserType.student.getValue()) {
			addUser = new Student();
			userDAO = new StudentDAOJdbcImpl();
		} else if (userType == UserType.admin.getValue()) {
			addUser = new Admin();
			userDAO = new AdminDAOJdbcImpl();
		}
		for (int i = 0; i < students.size(); i++) {
			Map<String, Object> student = students.get(i);
			addUser.setId(String.valueOf(student.get(columnMap.get("id"))));
			addUser.setName(String.valueOf(student.get(columnMap.get("name"))));
			addUser.setPassword(String.valueOf(student.get(columnMap.get("password"))));
			addUser.setPassword(Encoder.encodeByMD5(addUser.getPassword()));
			addUser.setClass(String.valueOf(student.get(columnMap.get("studentClass"))));
			addUser.setGroup(Integer.valueOf(String.valueOf(student.get(columnMap.get("group")))));
			addUser.setType(userType);
			if (userDAO.insert(addUser)) {
				count++;
			}
		}
		logger.debug("Insert student from Excel: " + count);
		return count;
	}
}
