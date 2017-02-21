/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File FileAction.java
 * @Time May 13, 2016 1:43:18 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import cn.edu.ustb.sem.datastructure.action.BaseAction;
import cn.edu.ustb.sem.datastructure.po.system.ExcelColumn;
import cn.edu.ustb.sem.datastructure.service.user.UserService;
import cn.edu.ustb.sem.datastructure.util.ExcelToAnything;
import cn.edu.ustb.sem.datastructure.util.GlobalEnum.UserType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description
 */
@Namespace("/")
public class FileAction extends BaseAction {
	private static final long	serialVersionUID	= -4141061752524666447L;
	private static Logger		logger				= Logger.getLogger(FileAction.class);

	@Action(value = "file", results = {
			@Result(name = "fileIndex", location = "/WEB-INF/page/common/fileUpload.jsp") })
	public String index() {
		System.out.println("Go to files!");
		return "fileIndex";
	}

	@Action(value = "matchColumnAdmin")
	public void matchColumnAdmin() throws IOException {
		String currentFilePath = (String) getSession("currentFilePath");
		logger.debug(currentFilePath);
		List<String> head = ExcelToAnything.getHead(currentFilePath);
		List<ExcelColumn> columns = new ArrayList<>();
		columns.add(new ExcelColumn("工号", "id", null));
		columns.add(new ExcelColumn("姓名", "name", null));
		columns.add(new ExcelColumn("密码", "password", null));
		JSONObject json = new JSONObject();
		json.element(SUCCESS, true);
		json.element("columns", columns);
		json.element("candidate", head);
		writeJson(json);
	}

	@SuppressWarnings("unchecked")
	@Action(value = "importAdmin")
	public void importAdmin() throws IOException {
		String currentFilePath = (String) getSession("currentFilePath");
		logger.debug(currentFilePath);
		String columnMatch = getParameter("columns");
		JSONArray columns = JSONArray.fromObject(columnMatch);
		Map<String, String> columnMap = new HashMap<>();
		for (int i = 0; i < columns.size(); i++) {
			Map<String, String> jsonMap = (Map<String, String>) columns.get(i);
			columnMap.put((String) jsonMap.get("id"), (String) jsonMap.get("columnInExcel"));
		}
		logger.debug(columnMap);
		int successCount = UserService.addAdminFromExcel(currentFilePath, columnMap,
				UserType.admin.getValue());
		logger.debug(successCount);
		JSONObject json = new JSONObject();
		if (successCount>0)
		{
			json.element(SUCCESS, true);
			json.element("count", successCount);
		}
		else
		{
			json.element(SUCCESS, false);
			json.element("message", "No valid user in Excel!");
		}
		writeJson(json);
	}
	

	@Action(value = "matchColumnStudent")
	public void matchColumnStudent() throws IOException {
		String currentFilePath = (String) getSession("currentFilePath");
		logger.debug(currentFilePath);
		List<String> head = ExcelToAnything.getHead(currentFilePath);
		List<ExcelColumn> columns = new ArrayList<>();
		columns.add(new ExcelColumn("学号", "id", null));
		columns.add(new ExcelColumn("姓名", "name", null));
		columns.add(new ExcelColumn("密码", "password", null));
		columns.add(new ExcelColumn("班级", "studentClass", null));
		columns.add(new ExcelColumn("分组", "group", null));
		JSONObject json = new JSONObject();
		json.element(SUCCESS, true);
		json.element("columns", columns);
		json.element("candidate", head);
		writeJson(json);
	}

	@SuppressWarnings("unchecked")
	@Action(value = "importStudent")
	public void importStudent() throws IOException {
		String currentFilePath = (String) getSession("currentFilePath");
		logger.debug(currentFilePath);
		String columnMatch = getParameter("columns");
		JSONArray columns = JSONArray.fromObject(columnMatch);
		Map<String, String> columnMap = new HashMap<>();
		for (int i = 0; i < columns.size(); i++) {
			Map<String, String> jsonMap = (Map<String, String>) columns.get(i);
			columnMap.put((String) jsonMap.get("id"), (String) jsonMap.get("columnInExcel"));
		}
		logger.debug(columnMap);
		int successCount = UserService.addStudentFromExcel(currentFilePath, columnMap,
				UserType.student.getValue());
		logger.debug(successCount);
		JSONObject json = new JSONObject();
		if (successCount>0)
		{
			json.element(SUCCESS, true);
			json.element("count", successCount);
		}
		else
		{
			json.element(SUCCESS, false);
			json.element("message", "No valid user in Excel!");
		}
		writeJson(json);
	}
}
