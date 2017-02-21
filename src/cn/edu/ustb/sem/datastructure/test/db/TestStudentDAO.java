/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File TestStudentDAO.java
 * @Time Dec 12, 2015 9:06:05 PM
 * @Author Smile
 * @Description Test whether StudentDAO works
 */
package cn.edu.ustb.sem.datastructure.test.db;

import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.user.impl.StudentDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.user.Student;
import cn.edu.ustb.sem.datastructure.po.user.User;

/**
 * @author Smile
 * @Description Test whether StudentDAO works
 */
public class TestStudentDAO {
	private static Logger logger = Logger.getLogger(TestStudentDAO.class);

	public static void main(String[] args) {
		List<User> students = null;
		Student student = new Student();
		StudentDAOJdbcImpl studentDAOJdbcImpl = new StudentDAOJdbcImpl();
		student.setId("41371086");
		student.setName("Smile");
		student.setPassword("3ddaeb82fbba964fb3461d4e4f1342eb");
		student.setClass("管信132");
		studentDAOJdbcImpl.insert(student);
		students = StudentDAOJdbcImpl.findByClass(student.getStudentClass());
		logger.info(students.toString());
		student.setName("孙梦瑶");
		studentDAOJdbcImpl.update(student);
		student = (Student) studentDAOJdbcImpl.findById(student.getId());
		logger.info(student.toString());
		student.setName("SmileAgain");
		studentDAOJdbcImpl.updateWithoutPassword(student);
		students = StudentDAOJdbcImpl.findByGroup(student.getType());
		logger.info(students.toString());
		students = StudentDAOJdbcImpl.findAll();
		logger.info(students.toString());
		studentDAOJdbcImpl.delete(student);
	}

}
