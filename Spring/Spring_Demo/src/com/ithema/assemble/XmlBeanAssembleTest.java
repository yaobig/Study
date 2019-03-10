package com.ithema.assemble;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanAssembleTest {
	public static void main(String[] args) {
		// 定义配置文件路径
		String xmlPath = "com/ithema/assemble/beans.xml";
		// 加载配置文件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		// 加载配置文件
		System.out.println(applicationContext.getBean("user1"));
		// 设值方法输出结果
		System.out.println(applicationContext.getBean("user2"));
	}
}
