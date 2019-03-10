package com.ithema.assemble;

import java.util.List;

public class User {
	private String username;
	private Integer password;
	private List<String> list;
	/**
	 * 1. 使用构造注入
	 * 1.1 提供带所有参数的有参数构造方法.
	 */
	public User(String username, Integer password, List<String> list) {
		super();
		this.username = username;
		this.password = password;
		this.list = list;
	}
	/**
	 * 2. 使用设值注入
	 * 2.1 提供默认空参数构造方法
	 * 2.2 为所有属性提供 setter 方法.
	 */
	public User() {
		super();
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(Integer password) {
		this.password = password;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", list=" + list + "]";
	}
}
