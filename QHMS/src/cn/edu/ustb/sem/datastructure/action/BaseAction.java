/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File BaseAction.java
 * @Time Jan 5, 2016 3:19:59 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**
 * @author Smile
 * @Description BaseAction, with some functions that can be used in any action
 */
public class BaseAction extends ActionSupport {

	// private static Logger logger = Logger.getLogger(BaseAction.class);

	private static final long serialVersionUID = 9206891357527051194L;

	/**
	 * @author Smile
	 * @Description return a json object to the browser
	 * @param String key
	 * @param Object value
	 * @throws IOException
	 */
	protected void writeJson(String key, Object value) throws IOException {
		JSONObject json = new JSONObject();
		json.element(key, value);
		writeJson(json.toString());
	}
	
	/**
	 * @author Smile
	 * @Description return a json object to the browser
	 * @param Object json
	 * @throws IOException
	 */
	protected void writeJson(Object json) throws IOException {
		writeJson(json.toString());
	}
	
	/**
	 * @author Smile
	 * @Description return a json string to the browser
	 * @param String json
	 * @throws IOException
	 */
	protected void writeJson(String json) throws IOException {
		HttpServletResponse response = getResponse();
		setResponse(response);
		PrintWriter out;
		out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}


	/**
	 * @author Smile
	 * @Description Get attribute by name
	 * @param name of attribute
	 * @return Object attribute
	 */
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	/**
	 * @author Smile
	 * @Description Set attribute of name to value
	 * @param name of attribute
	 * @param value
	 */
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	/**
	 * @author Smile
	 * @Description Get parameter by name
	 * @param name of parameter
	 * @return String
	 */
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * @author Smile
	 * @Description Get parameter array by name
	 * @param name of parameter
	 * @return String[]
	 */
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	/**
	 * @author Smile
	 * @Description Get session object by name
	 * @param name of session object
	 * @return Object Session
	 */
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	/**
	 * @author Smile
	 * @Description Get all the sessions
	 * @return Map<String, Object> All the sessions
	 */
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	/**
	 * @author Smile
	 * @Description Set session of name to value
	 * @param name
	 * @param value
	 */
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}
	
	/**
	 * @author Smile
	 * @Description Clear current session
	 */
	public void clearSession() {
		HttpSession session = getRequest().getSession(true);
		session.invalidate();
	}
	
	/**
	 * @author Smile
	 * @Description Set response to show Chinese word;
	 * @return HttpServletResponse
	 */
	public HttpServletResponse setResponse(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		return response;
	}

	/**
	 * @author Smile
	 * @Description Get Request
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * @author Smile
	 * @Description Get Response
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * @author Smile
	 * @Description Get Application
	 * @return ServletContext
	 */
	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

}
