package com.itheima.g_annotation.a_ioc;

import org.springframework.stereotype.Component;

@Component("userServiceId")
public class UserServiceImpl implements UserService{

	@Override
	public void addUser() {
			System.out.println("自动装配");
	}

}
