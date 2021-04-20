package com.test03;

import org.springframework.stereotype.Component;

@Component("igTV")
public class IgTv implements TV {

	public IgTv() {
		System.out.println("IgTv 생성");
	}
	
	@Override
	public void powerOn() {
		System.out.println("IgTv powerOn");

	}

	@Override
	public void powerOff() {
		System.out.println("IgTv powerOff");

	}

	@Override
	public void volumeUp() {
		System.out.println("IgTv volumeUp");

	}

	@Override
	public void volumeDown() {
		System.out.println("IgTv volumeDown");

	}

}
