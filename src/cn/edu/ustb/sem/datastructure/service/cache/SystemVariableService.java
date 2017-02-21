/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File SystemVariableService.java
 * @Time Mar 13, 2016 5:07:49 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.service.cache;

import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.ustb.sem.datastructure.dao.system.impl.SystemVariableDAOJdbcImpl;
import cn.edu.ustb.sem.datastructure.po.system.SystemVariable;
import cn.edu.ustb.sem.datastructure.util.SystemVariableCache;

/**
 * @author Smile
 * @Description
 */
public class SystemVariableService {
	private static Logger logger = Logger.getLogger(SystemVariableService.class);

	public static void initSystemVariable() {
		List<SystemVariable> systemVariables = SystemVariableDAOJdbcImpl.findAll();
		SystemVariableCache.cache.clear();
		for (int i = 0; i < systemVariables.size(); i++) {
			SystemVariableCache.cache.put(systemVariables.get(i).getName(), systemVariables.get(i));
		}
		logger.info("System variables loaded!");
	}

	public static void reloadSystemVariable() {
		initSystemVariable();
	}

}
