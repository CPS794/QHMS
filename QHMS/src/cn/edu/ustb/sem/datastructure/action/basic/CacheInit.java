package cn.edu.ustb.sem.datastructure.action.basic;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.service.cache.SystemVariableService;

/**
 * Servlet implementation class CacheInit
 */
@WebServlet(value="/CacheInit", loadOnStartup=1)
public class CacheInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger		logger				= Logger.getLogger(CacheInit.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CacheInit() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.info("Start loading cache...");
		SystemVariableService.initSystemVariable();
	}

}
