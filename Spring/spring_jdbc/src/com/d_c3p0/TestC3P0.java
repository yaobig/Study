package com.d_c3p0;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.a_domain.User;

public class TestC3P0 {
	@Test
	public void demo02 () {
		User user = new User();
		user.setId(1);
		user.setUsername("接客");
		user.setPassword("999");
		
		
		String xmlPath = "com/d_c3p0/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		
		UserDao userDao = (UserDao) applicationContext.getBean("UserDaoId");
		userDao.updata(user);
		List<User> allUser = userDao.findAll();
		/*for (User user : allUser) {
			System.out.println(user);
		}*/
	}
}
