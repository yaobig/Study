package com.itheima.a_proxy.b_cglib;

public class MyAspect {
	public void before() {
		System.out.println("头");
	} 
	public void after() {
		System.out.println("尾");
	}
}
