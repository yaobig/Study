package com.Test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.AccountServcie;

public class TestApp {
	
	@Test
	public void Demo() {
		String xmlPath = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		AccountServcie accountServcie = (AccountServcie) applicationContext.getBean("accountService");
		accountServcie.transfer("rose", "jack", 1000);
	}
}
