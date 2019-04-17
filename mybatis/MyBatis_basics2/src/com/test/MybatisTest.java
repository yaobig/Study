package com.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.po.Customer;
import com.utils.MybatisUtils;

public class MybatisTest {
	/**
	 * 根据客户编号查询客户信息
	 */
	@Test
	public void findCustomerByIdTest()throws Exception{
		MybatisUtils mybatisUtils = new MybatisUtils();
		Customer customer = mybatisUtils.getSession().selectOne("com.mapper" + ".CustomerMapper.findCustomerById" , 2);
		System.out.println(customer.toString());
		List<Customer> customers = mybatisUtils.getSession().selectList("com.mapper" + ".CustomerMapper.findCustomerByName", "j");
		for (Customer customer2 : customers) {
			System.out.println(customer2.toString());
		}
	}
	@Test
	public void addCustomerTest() {
		// 获取SqlSession
		SqlSession sqlSession = MybatisUtils.getSession();
		Customer customer = new Customer();
		customer.setUsername("wcx");
		customer.setJobs("stuent");
		customer.setPhone("job4");
		int rows = sqlSession.insert("com.mapper" + ".CustomerMapper.addCustomer", customer);
		// 输出插入数据的主键 id 值
		System.out.println(customer.getId());
		if (rows > 0) {
			System.out.println("您成功插入了"+rows+"条数据");
		}else {
			System.out.println("执行插入操作失败!!!");
		}
		// 提交事务
		sqlSession.commit();
		sqlSession.close();
	}
}
