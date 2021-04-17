package com.test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mtest {

	public static void main(String[] args) {
	
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/test01/applicationContext.xml");
		// banana
		MessageBean banana = (MessageBean) factory.getBean("banana");
		banana.sayHello();
		//strawberry
		MessageBean strawberry = (MessageBean) factory.getBean("strawberry");	
		strawberry.sayHello();
		//kiwi
		MessageBean kiwi = (MessageBean) factory.getBean("kiwi");	
		kiwi.sayHello();
		//cherry
		MessageBean cherry = (MessageBean) factory.getBean("cherry");
		cherry.sayHello();
		
	}
	
	
	
	
}
