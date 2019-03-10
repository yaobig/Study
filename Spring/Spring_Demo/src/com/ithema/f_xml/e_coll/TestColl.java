package com.ithema.f_xml.e_coll;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestColl {
	@Test
	public void demo02 () {
		String xmlPath = "com/ithema/f_xml/e_coll/beans.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		CollData collDataId = (CollData) applicationContext.getBean("collDataId");
		System.out.println(collDataId);
	}
}
