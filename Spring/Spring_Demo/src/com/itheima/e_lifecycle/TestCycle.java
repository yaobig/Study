package com.itheima.e_lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCycle {
	@Test
	public void Demo() {
		// spring 工厂
		String xmlPath = "com/itheima/e_lifecycle/beans.xml";
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		UserService userService = (UserService) applicationContext.getBean("userServiceId");
		userService.addUser();
		
		// 要求， 1. 容器必须 close， 销毁方法执行。 2. 必须单例的
		// 此方法接口没有定义， 实现类提供
		applicationContext.close();
	}
}
