package com.utils;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 工具类
 * @author 11
 *
 */
public class MybatisUtils {
	private static SqlSessionFactory sqlSessionFactory = null;
	// 初始化 SqlSessionFactory
	static {
		try {
			// 使用 Mybatis 提供的 Resource 类加载 Mybatis 的配置文件
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			// 构建 SqlSessionFactory 工厂
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 获取 SQLSession 对象的静态方法
	public static SqlSession getSession(){
		return sqlSessionFactory.openSession();
	}
}
