package com.c_dbcp;

import org.springframework.jdbc.core.JdbcTemplate;

import com.a_domain.User;


public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void updata(User user) {
		String sql = "updata t_user set username=?, password=? where id =?";
		Object[] args = {user.getUsername(),user.getPassword(),user.getId()};
		jdbcTemplate.update(sql,args);
	}
}
