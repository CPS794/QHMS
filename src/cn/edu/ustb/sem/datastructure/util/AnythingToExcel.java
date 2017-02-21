/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File AnythingToExcel.java
 * @Time May 26, 2016 6:16:45 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.util;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.ustb.sem.datastructure.po.system.ExcelColumn;
import net.sf.json.JSONArray;

/**
 * @author Smile
 * @Description
 */
public class AnythingToExcel {

	private static Logger logger = Logger.getLogger(AnythingToExcel.class);

	public static void listToExcel(List<ExcelColumn> thead, List<Map<String, Object>> table,
			String fileName, String sheetName) {
		try {
			// 创建名为 sheetName 的工作表
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetName);

			// 创建表头
			HSSFRow head = sheet.createRow(0);
			HSSFCell headCell;
			for (int i = 0; i < thead.size(); i++) {
				headCell = head.createCell(i);
				headCell.setCellValue(thead.get(i).getDisplayName());
			}

			// 填充表内容
			for (int i = 0; i < table.size(); i++) {
				Map<String, Object> thisRow = table.get(i);
				HSSFRow row = sheet.createRow(i + 1);
				HSSFCell cell;
				for (int j = 0; j < thead.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(thisRow.get(thead.get(j).getId()).toString());
				}
			}

			// 文件流输出
			FileOutputStream os = null;
			logger.debug(fileName);
			os = new FileOutputStream(fileName);
			workbook.write(os);
			os.flush();
			os.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void jsonArrayToExcel(JSONArray thead, JSONArray table, String fileName,
			String sheetName) {
		try {
			// 创建名为 sheetName 的工作表
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetName);

			// 创建表头
			HSSFRow head = sheet.createRow(0);
			HSSFCell headCell;
			for (int i = 0; i < thead.size(); i++) {
				headCell = head.createCell(i);
				headCell.setCellValue(thead.getString(i));
			}

			// 填充表内容
			for (int i = 0; i < table.size(); i++) {
				HSSFRow row = sheet.createRow(i + 1);
				HSSFCell cell;
				JSONArray tr = (JSONArray) table.get(i);
				for (int j = 0; j < tr.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(tr.getString(j));
				}
			}

			// 文件流输出
			FileOutputStream os = null;
			logger.debug(fileName);
			os = new FileOutputStream(fileName);
			workbook.write(os);
			os.flush();
			os.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
