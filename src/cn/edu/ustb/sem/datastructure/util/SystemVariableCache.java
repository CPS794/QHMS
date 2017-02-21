/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File SystemVariableCache.java
 * @Time Mar 13, 2016 4:34:13 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.util;

import java.util.HashMap;
import java.util.Map;

import cn.edu.ustb.sem.datastructure.po.system.SystemVariable;

/**
 * @author Smile
 * @Description
 */
public class SystemVariableCache {
	public static Map<String, SystemVariable> cache = new HashMap<>();

	private SystemVariableCache() {
	};

	private static final SystemVariableCache SYSTEM_VARIABLE_CACHE = new SystemVariableCache();
	
	public static SystemVariableCache getInstance() {
		return SYSTEM_VARIABLE_CACHE;
	}
}
