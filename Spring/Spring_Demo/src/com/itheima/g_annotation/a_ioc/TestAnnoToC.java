package com.itheima.g_annotation.a_ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnoToC {
	@Test
	public void demo01() {
		String xmlPath = "com/itheima/g_annotation/a_ioc/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService service = (UserService) applicationContext.getBean("userServiceId");
		service.addUser();
	}
}
