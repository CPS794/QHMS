/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Admin.java
 * @Time Dec 12, 2015 8:13:41 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.user;

import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;

/**
 * @author Smile
 * @Description
 */
public class Admin extends User {

	public Admin() {
		setType(UserType.admin.getValue());
	}

	@Override
	public String toString() {
		return "Admin [id=" + getId() + ", name=" + getName() + ", password=" + getPassword() + ", studentClass=" + getStudentClass() + ", group=" + getGroup() + ", type=" + getType() + ", valid=" + isValid() + "]";
	}
}
