package com.itheima.d_aspect.a_xml;

public class UserServiceImpl implements UserService{

	@Override
	public void addUser() {
		System.out.println("a_porxy.a_jdk addUser");
	}

	@Override
	public String updateUser() {
		System.out.println("a_porxy.a_jdk updateUser");
		// 环绕中的异常
		// int i = 1/0;
		return "lalala";
	}

	@Override
	public void deleteUser() {
		System.out.println("a_porxy.a_jdk deleteUser");
	}

}
