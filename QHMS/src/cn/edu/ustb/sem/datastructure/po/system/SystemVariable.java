/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File SystemVariable.java
 * @Time Mar 9, 2016 3:07:23 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.system;

/**
 * @author Smile
 * @Description
 */
public class SystemVariable {
	private String	name;
	private String	chineseName;
	private String	value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
