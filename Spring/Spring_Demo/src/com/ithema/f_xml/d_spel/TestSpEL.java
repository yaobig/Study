package com.ithema.f_xml.d_spel;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpEL {
	@Test
	public void demo02 () {
		String xmlPath = "com/ithema/f_xml/d_spel/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		Customer customer = (Customer) applicationContext.getBean("customerId");
		System.out.println(customer);
	}
}
