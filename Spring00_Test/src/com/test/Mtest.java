package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mtest {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/test/beans.xml");
		
		String name = (String) factory.getBean("name");
		
		System.out.println(name);
		
	}	
}
