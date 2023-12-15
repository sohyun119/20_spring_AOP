package com.spring.aop;

import org.springframework.stereotype.Component;

// 테스트
@Component
public class Employee {

	public void work() {
		System.out.println("직원의 일을 한다.");
	}
	
	public void getWorkingTime() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getInfo(String title , int salary) {
		
	}
	
}
