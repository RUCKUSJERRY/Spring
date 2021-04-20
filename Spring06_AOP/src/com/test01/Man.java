package com.test01;

public class Man {

	public void classWork() {
		System.out.println("출석한다.");
		
		try {
			System.out.println("컴퓨터를 켜서 주식본다.");
		} catch(Exception e) {
			System.out.println("쉬는날 이었다.");
		} finally {
			System.out.println("집에 간다.");
		}
	}
	
}
