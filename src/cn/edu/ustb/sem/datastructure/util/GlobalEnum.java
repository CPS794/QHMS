/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File GlobalEnum.java
 * @Time Mar 2, 2016 3:52:34 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.util;

/**
 * @author Smile
 * @Description
 */
public class GlobalEnum {
	/**
	 * @author Smile
	 * @Description transfer user type to int
	 */
	public enum UserType {
		student(0), admin(1);
		private final int value;

		public int getValue() {
			return value;
		}

		UserType(int value) {
			this.value = value;
		}
	}
	
	public enum Judge {
		automatic(0), manual(1);
		private final int value;

		public int getValue() {
			return value;
		}

		Judge(int value) {
			this.value = value;
		}
	}
	
	public static String getChineseNumber(int index) {
		String chineseNumber = new String();
		chineseNumber="";
		if (index<0)
		{
			chineseNumber+="负";
			index=-index;
		}
		String number[] = new String[20];
		number[0]="零";
		number[1]="一";
		number[2]="二";
		number[3]="三";
		number[4]="四";
		number[5]="五";
		number[6]="六";
		number[7]="七";
		number[8]="八";
		number[9]="九";
		number[10]="十";
		return chineseNumber+number[index];
	}
}
