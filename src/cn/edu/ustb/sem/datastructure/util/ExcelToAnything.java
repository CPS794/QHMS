/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File ExcelToAnything.java
 * @Time May 14, 2016 1:01:40 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Smile
 * @Description
 */
public class ExcelToAnything {
	private static Logger logger = Logger.getLogger(ExcelToAnything.class);

	public static List<String> getHead(String fileName) {
		List<String> theadCells = new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row thead = rowIterator.next();
			Iterator<Cell> theadIterator = thead.cellIterator();
			while (theadIterator.hasNext()) {
				Cell cell = theadIterator.next();
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						theadCells.add("" + cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						theadCells.add(cell.getStringCellValue());
						break;
				}
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theadCells;
	}

	public static List<Map<String, Object>> excelToList(String fileName) {
		List<Map<String, Object>> dataInExcel = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row thead = rowIterator.next();
			Iterator<Cell> theadIterator = thead.cellIterator();
			List<String> theadCells = new ArrayList<String>();
			while (theadIterator.hasNext()) {
				Cell cell = theadIterator.next();
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						theadCells.add("" + cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						theadCells.add(cell.getStringCellValue());
						break;
				}
			}
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Map<String, Object> thisRow = new HashMap<String, Object>();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				for (String theadCell : theadCells) {
					if (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								thisRow.put(theadCell, cell.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								thisRow.put(theadCell, cell.getStringCellValue());
								break;
						}
					} else {
						thisRow.put(theadCell, null);
					}
				}
				dataInExcel.add(thisRow);
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug(dataInExcel.toString());
		return dataInExcel;
	}

	public static List<Map<String, Object>> excelToList_AllString(String fileName) {
		List<Map<String, Object>> dataInExcel = new ArrayList<>();
		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			Row thead = rowIterator.next();
			Iterator<Cell> theadIterator = thead.cellIterator();
			List<String> theadCells = new ArrayList<String>();
			while (theadIterator.hasNext()) {
				Cell cell = theadIterator.next();
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						int temp = (int) cell.getNumericCellValue();
						theadCells.add(String.valueOf(temp));
						break;
					case Cell.CELL_TYPE_STRING:
						theadCells.add(cell.getStringCellValue());
						break;
				}
			}
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Map<String, Object> thisRow = new HashMap<String, Object>();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				for (String theadCell : theadCells) {
					if (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								int temp = (int) cell.getNumericCellValue();
								thisRow.put(theadCell, String.valueOf(temp));
								break;
							case Cell.CELL_TYPE_STRING:
								thisRow.put(theadCell, cell.getStringCellValue());
								break;
						}
					} else {
						thisRow.put(theadCell, null);
					}
				}
				dataInExcel.add(thisRow);
			}
			workbook.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug(dataInExcel.toString());
		return dataInExcel;
	}
}
