package com.test02;

public class MTest {

	public static void main(String[] args) {
		
		MessageBean korea = new MessageBeanKo();
		korea.sayHello("스프링");
		
		MessageBean english = new MessageBeanEn();
		english.sayHello("Spring");
	}
	
}
