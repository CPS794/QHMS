/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File Column.java
 * @Time May 14, 2016 2:00:58 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.po.system;

/**
 * @author Smile
 * @Description
 */
public class ExcelColumn {
	private String displayName;
	private String id;
	private String columnInExcel;
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the columnInExcel
	 */
	public String getColumnInExcel() {
		return columnInExcel;
	}
	/**
	 * @param columnInExcel the columnInExcel to set
	 */
	public void setColumnInExcel(String columnInExcel) {
		this.columnInExcel = columnInExcel;
	}
	public ExcelColumn() {
		// Empty constructor
	}
	public ExcelColumn(String name, String id, String column) {
		this.displayName=name;
		this.id=id;
		this.columnInExcel=column;
	}
}
