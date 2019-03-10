package com.b_api;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestApi {
	public static void main(String[] args) {
		
		// 1 创建数据源 (连接池) dbcp
		BasicDataSource dataSource = new BasicDataSource();
		// * 基本 4 项
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_day");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		// 2 创建模板
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		// 3 通过  api 操作
		jdbcTemplate.update("insert into t_user(username,password) values(?,?);", "tom" , "998");
		
		
		
	}
}
