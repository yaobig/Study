package com.itheima.a_proxy.c_spring_aop;

public class UserServiceImpl implements UserService{

	@Override
	public void addUser() {
		System.out.println("c_spring_aop addUser");
	}

	@Override
	public void updateUser() {
		System.out.println("c_spring_aop updateUser");
	}

	@Override
	public void deleteUser() {
		System.out.println("c_spring_aop deleteUser");
	}

}
