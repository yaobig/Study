package com.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.po.User;
import com.utils.MybatisUtils;

public class MybatisTest {
	@Test
	public void findAllUserTest() {
		// 获取 SqlSession
		SqlSession sqlSession = MybatisUtils.getSession();
		// SQLSession 执行映射文件中定义的 SQL, 并返回映射结果
		List<User> list = sqlSession.selectList("com.mapper.UserMapper.findAllUser");
		for (User user : list) {
			System.out.println(user);
		}
		// 关闭SQLSession
		sqlSession.close();
	}
}
