package com.itheima.d_aspect.b_anno;

import org.springframework.stereotype.Service;

@Service("userServiceId")
public class UserServiceImpl implements UserService{

	@Override
	public void addUser() {
		System.out.println("d_aspect.b_anno addUser");
	}

	@Override
	public String updateUser() {
		System.out.println("d_aspect.b_anno updateUser");
		// 环绕中的异常
		int i = 1/0;
		return "lalala";
	}

	@Override
	public void deleteUser() {
		System.out.println("d_aspect.b_anno deleteUser");
	}

}
