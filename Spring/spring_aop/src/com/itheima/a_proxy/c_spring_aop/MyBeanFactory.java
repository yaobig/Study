package com.itheima.a_proxy.c_spring_aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {

	public static UserService createService() {
		// 1. 目标类
		final UserService userService = new UserServiceImpl();
		// 2. 切面类
		final MyAspect myaspect = new MyAspect();
		/*	3. 代理类: 将目标类 （切入点） 和 切面类 （通知） 结合 --> 切面
		 *  Proxy.newProxyInstance
		 *  	参数1: loader， 类加载器， 动态代理类 运行时创建， 任何类都需要类加载器将其加载到内存
		 *  		一般情况： 当前类.class.getClassLoafer();
		 *  				目标类实例.getClass().get...
		 *  	参数2:Class[] interfaces 代理类需要实现的所有接口
		 *  			方式1： 目标列实例、getClass().getInterfaces();  注意： 只能获得自己接口， 不能获得父元素接口
		 *  			方式2：Class[]{UserService.class}
		 *  			例如：jdbc 驱动  --> DriverManager  获得接口 Connection
		 *  	参数3:InocationHandler  处理类， 接口， 必须进行实现类， 一般采用匿名内部类
		 *  		提供 invoke 方法， 代理类的每一个方法执行时， 都将调用一次 invoke
		 *  			参数31: Object proxy : 代理对象
		 *  			参数32: Method method : 代理对象当前执行的方法的描述对象 (反射)
		 *  					执行方法名: method.getName()
		 *  					执行方法: method.invoke(对象, 实际参数) 
		 *  			参数33: Object[] args : 方法实际参数
		 *  
		 */
		UserService proxService = (UserService) Proxy.newProxyInstance(
									MyBeanFactory.class.getClassLoader(),
									userService.getClass().getInterfaces(), 
									new InvocationHandler() {
										
										@Override
										public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
											
											// 前执行
											//myaspect.before();
											
											// 执行目标类的方法
											method.invoke(userService, args);
											// 后执行
											//myaspect.after();
											
											return null;
										}
									});
		return  proxService;
	}
}
