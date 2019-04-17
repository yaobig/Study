package com.test;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.po.Person;
import com.utils.MybatisUtils;

/**
 * MyBatis 关联查询映射测试类
 * @author 11
 *
 */
public class MybatisAssociatedTest {
	
	/**
	 * 嵌套查询
	 */
	@Test
	public void findPersonByIdTest2 () {
		// 1. 通过工具类生成 SQLSession 对象
		SqlSession session = MybatisUtils.getSession();
		// 2. 使用 MyBatis 嵌套查询的方式查询  id 为 1 的人的信息
		Person person = session.selectOne("com.mapper.PersonMapper"+".findPersonById2",1);
		// 3. 输出
		System.out.println(person);
		// 4. 关闭
		session.close();
	}
	
	/**
	 * 嵌套查询
	 */
	@Test
	public void findPersonByIdTest() {
		// 1. 通过工具类生成 SQLSession 对象
		SqlSession session = MybatisUtils.getSession();
		// 2. 使用 MyBatis 嵌套查询的方式查询  id 为 1 的人的信息
		Person person = session.selectOne("com.mapper.PersonMapper"+".findPersonById",1);
		// 3. 输出
		System.out.println(person);
		// 4. 关闭
		session.close();
	}
}
