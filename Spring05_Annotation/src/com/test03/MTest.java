package com.test03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MTest {

	public static void main(String[] args) {
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("com/test03/applicationContext.xml");
		
		TV samsong = factory.getBean("samsong", SamsongTv.class);
		samsong.powerOn();
		samsong.powerOff();
		samsong.volumeUp();
		samsong.volumeDown();
		
		TV ig = (TV) factory.getBean("igTV");
		ig.powerOn();
		ig.powerOff();
		ig.volumeUp();
		ig.volumeDown();
	}
	
}
