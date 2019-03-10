package com.itheima.a_proxy.b_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import sun.security.jgss.spi.MechanismFactory;

public class MyBeanFactory {

	public static UserServiceImpl createService() {
		// 1. 目标类
		final UserServiceImpl userService = new UserServiceImpl();
		// 2. 切面类
		final MyAspect myaspect = new MyAspect();
		
		
		// 3. 代理类 , 采用 cglib, 底层创建目标类的子类

		
		// 3.1 核心类
		Enhancer enhancer = new Enhancer();
		// 3.2 确认父类
		enhancer.setSuperclass(userService.getClass());
		/* 3.3 设置回调函数， MethodInterceptor 接口 等效 jdk InvocationHandler 接口
		 *	intercept() 等效于 jdk invoke()
		 *
		 */
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				
				
				// 前
				myaspect.before();
				
				// 执行目标类的方法
				Object obj = method.invoke(userService, args);
				// * 执行代理类的父类, 执行目标类 (目标类和代理类 父子关系)
				methodProxy.invokeSuper(proxy, args);
				
				// 后
				myaspect.after();
				
				return obj;
			}
		});
		// 3.4 创建代理
		UserServiceImpl proxService = (UserServiceImpl) enhancer.create();
		
		
		return  proxService;
	}
}
