package com.c_dbcp;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.a_domain.User;

public class TestDBCP {
	@Test
	public void demo02 () {
		User user = new User();
		user.setId(1);
		user.setUsername("接客");
		user.setPassword("998");
		
		
		String xmlPath = "com/c_dbcp/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		
		
		UserDao userDao = (UserDao) applicationContext.getBean("UserDaoId");
		userDao.updata(user);
	}
}
