/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Student.java
 * @Time Dec 12, 2015 8:11:51 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.user;

import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;

/**
 * @author Smile
 * @Description
 */
public class Student extends User {

	public Student() {
		setType(UserType.student.getValue());
	}

	@Override
	public String toString() {
		return "Student [id=" + getId() + ", name=" + getName() + ", password=" + getPassword() + ", studentClass=" + getStudentClass() + ", group=" + getGroup() + ", type=" + getType() + ", valid=" + isValid() + "]";
	}
}
