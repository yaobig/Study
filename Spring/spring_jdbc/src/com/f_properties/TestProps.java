package com.f_properties;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.a_domain.User;

public class TestProps {
	@Test
	public void demo02 () {
		/*User user = new User();
		user.setId(1);
		user.setUsername("接客");
		user.setPassword("999");*/
		
		
		String xmlPath = "com/f_properties/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		
		UserDao userDao = (UserDao) applicationContext.getBean("UserDaoId");
		//userDao.updata(user);
		/*List<User> allUser = userDao.findAll();
		for (User user : allUser) {
			System.out.println(user);
		}*/
		User user = userDao.getById(1);
		System.out.println(user);
	}
}
