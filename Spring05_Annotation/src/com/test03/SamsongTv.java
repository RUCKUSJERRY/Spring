package com.test03;

import org.springframework.stereotype.Component;

@Component("samsong")
public class SamsongTv implements TV {

	public SamsongTv() {
		System.out.println("SamsongTv 생성");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsongTv powerOn");

	}

	@Override
	public void powerOff() {
		System.out.println("SamsongTv powerOff");

	}

	@Override
	public void volumeUp() {
		System.out.println("SamsongTv volumeUp");

	}

	@Override
	public void volumeDown() {
		System.out.println("SamsongTv volumeDown");

	}

}
