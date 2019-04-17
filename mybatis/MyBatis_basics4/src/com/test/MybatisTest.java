package com.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.po.Customer;
import com.utils.MybatisUtils;

public class MybatisTest {
	
	/**
	 * <bind> 元素的使用: 根据客户名模糊查询客户信息
	 */
	@Test
	public void findCustomerByNameTest() {
		// 获取 SQLSession
		SqlSession sqlSession = MybatisUtils.getSession();
		// 创建 Customer 对象, 封装查询的条件
		Customer customer = new Customer();
		customer.setUsername("j");
		List<Customer> customers = sqlSession.selectList("com.mapper.CustomerMapper.findCustomerByName", customer);
		for (Customer customer2 : customers) {
			System.out.println(customer2);
		}
		sqlSession.close();
	}
	
	
	
	
	/**
	 * 根据客户编号批量查询客户信息
	 */
//	@Test
//	public void findCustomerByIdsTest() {
//		// 获取 SQLSession
//		SqlSession sqlSession = MybatisUtils.getSession();
//		// 创建 List 集合, 封装查询 id
//		List<Integer> ids = new ArrayList<Integer>();
//		ids.add(1);
//		ids.add(2);
//		// 执行 SQLSession 的查询方法, 并返回结果集
//		List<Customer> customer =  sqlSession.selectList("com.mapper.CustomerMapper.findCustomerByIds",ids);
//		// 输出查询结果信息
//		for (Customer customer2 : customer) {
//			System.out.println(customer2);
//		}
//		sqlSession.close();
//	}
	
	
	
//	@Test
//	public void updateCustomer() {
//		// 获取 SqlSession
//		SqlSession sqlSession = MybatisUtils.getSession();
//		// 创建 Customer 对象, 并向对象中添加数据
//		Customer customer = new Customer();
//		customer.setId(3);
//		customer.setPhone("1111111");
//		// 执行 SQLSession 的更新方法， 范湖id是SQL 语句影响的行数
//		int rows = sqlSession.update("com.mapper.CustomerMapper.updateCustomer",customer);
//		if (rows > 0) {
//			System.out.println("您成功修改了"+rows+"条数据!");
//		} else {
//			System.out.println("执行修改操作失败!!!");
//		}
//		sqlSession.commit();
//		sqlSession.close();
//	}
//	
	
	
	/**
	 * 根据用户姓名和职业组合条件查询客户信息列表
	 */
//	@Test
//	public void findCustomerByNameAndJobsTest(){
//		// 通过工具类生成 SQLSession 对象
//		SqlSession sqlSession = MybatisUtils.getSession();
//		// 创建 Customer 对象， 封装需要组合查询的条件
//		Customer customer = new Customer();
//		// 如果如下两行代码被注释掉，则会表中所有数据被输出
//		/*customer.setUsername("jack");
//		customer.setJobs("job1");*/
//		// 执行 SQLSession 的查询方法， 返回结果集
//		List<Customer> customers = sqlSession.selectList("com.mapper.CustomerMapper.findCustomerByNameAndJobs",customer);
//		// 输出查询结果信息
//		for (Customer customer2 : customers) {
//			System.out.println(customer2);
//		}
//		// 关闭 SQLSession
//		sqlSession.close();
//	}
}
