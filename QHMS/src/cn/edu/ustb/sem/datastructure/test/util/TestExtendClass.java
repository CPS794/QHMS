/**
 * @Copyright (c) 2015 USTB SEM Smile.All Rights reserved.
 * @Project QHMS
 * @File TestExtendClass.java
 * @Time Mar 4, 2016 7:22:10 PM
 * @Author Smile
 * @Description
 */
package cn.edu.ustb.sem.datastructure.test.util;

/**
 * @author Smile
 * @Description Test about abstract class with static method
 */
public class TestExtendClass {
	public static void main(String[] args) {
		Father father=null;
		Child child=new Child();
		father.name();
		child.name();
		father=child;
		father.name();
	}
}

class Child extends Father {
	public static void name() {
		System.out.println("Child!");
	}
}

class Father {
	public static void name() {
		System.out.println("Father!");
	}
}