/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File User.java
 * @Time Dec 12, 2015 7:54:06 PM
 * @Author Smile
 * @Description User means administrator and students
 */
package cn.edu.ustb.sem.datastructure.po.user;

/**
 * @author Smile
 * @Description Basic user, can be either student or administrator
 */
public class User {
	private String	id;
	private String	name;
	private String	password;
	private String	studentClass;
	private Integer	group;
	private Integer	type;
	private boolean	valid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", studentClass=" + studentClass + ", group=" + group + ", type=" + type + ", valid=" + valid + "]";
	}
}
