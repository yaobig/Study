package com.itheima.e_lifecycle;

public class UserServiceImpl implements UserService{

	public void addUser() {
		System.out.println("e_lifecycle add user");
	}
	public void myInit() {
		System.out.println("初始化");
	}
	public void myDestroy() {
		System.out.println("销毁");
	}
}
