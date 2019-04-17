package com.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.po.Customer;

public class MybatisTest {
	/**
	 * 根据客户编号查询客户信息
	 */
	@Test
	public void findCustomerByIdTest()throws Exception{
		// 1. 读取配置文件
		String resouce = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resouce);
		// 2. 根据配置文件构建  SqlSeesionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 3. 通过 SqlSessionFactory 创建 SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 查询
		
		// 4. SqlSession 执行映射文件中定义的 SQL ， 并返回映射结果
		/*Customer customer = sqlSession.selectOne("com.mapper" + ".CustomerMapper.findCustomerById" , 2);
		// 打印输出结果
		System.out.println(customer.toString());
		// 模糊搜索
		List<Customer> customers = sqlSession.selectList("com.mapper" + ".CustomerMapper.findCustomerByName", "j");
		// 打印输出结果
		for (Customer customer2 : customers) {
			System.out.println(customer2);
		}*/
		
		// 插入
		
		/*// 4.1 创建 Customer对象， 并向对象中添加数据
		Customer customer = new Customer();
		customer.setUsername("rose");
		customer.setJobs("student");
		customer.setPhone("1333333333");
		// 4.2 执行  sqlSession 的插入方法， 返回的是 SQL 语句影响的行数
		int rows = sqlSession.insert("com.mapper" + ".CustomerMapper.addCustomer", customer);
		// 4.3 通过返回结果判断插入操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功插入了"+rows+"条数据");
		}else {
			System.out.println("执行插入操作失败!!!");
		}
		// 4.4 提交事务
		sqlSession.commit();*/
		
		
		
		
		// 更新
		
		// 4. 创建 Customer对象，对对象中的数据进行模拟更新
		/*Customer customer = new Customer();
		customer.setId(4);
		customer.setUsername("rose");
		customer.setJobs("programmer");
		customer.setPhone("13322222");
		// 4.2  执行 sqlSession 的更新方法， 返回的是 SQL 语句影响的行数
		int rows = sqlSession.update("com.mapper" + ".CustomerMapper.updateCustomer", customer);
		// 4.3 通过返回结果判断插入操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功修改了"+rows+"条数据");
		}else {
			System.out.println("执行修改操作失败!!!");
		}
		// 4.4 提交事务
		sqlSession.commit();
		// 5. 关闭 SqlSession 
*/		
		
		
		// 删除
		// 4.1 执行   SqlSession 的删除方法， 返回的是  SQL 语句影响的行数
		int rows = sqlSession.delete("com.mapper" + ".CustomerMapper.deleteCustomer", 4);
		// 4.2 通过返回结果判断插入操作是否执行成功
		if (rows > 0) {
			System.out.println("您成功删除了"+rows+"条数据");
		}else {
			System.out.println("执行删除操作失败!!!");
		}
		// 4.3 提交事务
		sqlSession.commit();
		sqlSession.close();
	}
}
