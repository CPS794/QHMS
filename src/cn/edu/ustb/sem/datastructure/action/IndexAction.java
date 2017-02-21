/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File IndexAction.java
 * @Time Jan 5, 2016 3:24:47 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -7382586483254440018L;

	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/index.jsp") })
	public String index() {
		System.out.println("This is a test!");
		return "index";
	}
}
