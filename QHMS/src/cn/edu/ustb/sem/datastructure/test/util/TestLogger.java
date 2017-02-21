/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File TestLogger.java
 * @Time Dec 12, 2015 6:42:24 PM
 * @Author Smile
 * @Description Test creating log files using Logger
 */
package cn.edu.ustb.sem.datastructure.test.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Smile
 * @Description Test creating log files using Logger, including log to console
 *              and log to file
 */
public class TestLogger {
	/**
	 * @author Smile
	 * @Description Default log will be printed in console above INFO
	 */
	public static void defaultLog() {
		Logger logger = Logger.getLogger(TestLogger.class.getName());
		logger.log(Level.WARNING, "This is a waring");
		logger.log(Level.INFO, "This is an info");
		logger.log(Level.CONFIG, "This is a config");
	}

	/**
	 * @author Smile
	 * @Description Level can be changed and will be the smaller one of logger
	 *              and handler
	 */
	public static void leveledLog() {
		Logger logger = Logger.getLogger(TestLogger.class.getName());
		logger.setLevel(Level.CONFIG);
		for (Handler handler : logger.getParent().getHandlers()) {
			handler.setLevel(Level.CONFIG);
		}
		logger.log(Level.WARNING, "This is a waring");
		logger.log(Level.INFO, "This is an info");
		logger.log(Level.CONFIG, "This is a config");
		logger.log(Level.FINE, "This is a fine");
	}

	/**
	 * @author Smile
	 * @Description Logs can also write to file using addHandler
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void toFileLog() throws SecurityException, IOException {
		Logger logger = Logger.getLogger(TestLogger.class.getName());
		logger.setLevel(Level.CONFIG);
		FileHandler handler = new FileHandler("WebContent/WebRoot/log/config%g.log");
		handler.setLevel(Level.CONFIG);
		logger.addHandler(handler);
		logger.log(Level.WARNING, "This is a waring");
		logger.log(Level.INFO, "This is an info");
		logger.log(Level.CONFIG, "This is a config");
	}

	public static void main(String[] args) throws SecurityException, IOException {
		// defaultLog();
		 leveledLog();
		// toFileLog();
	}
}
