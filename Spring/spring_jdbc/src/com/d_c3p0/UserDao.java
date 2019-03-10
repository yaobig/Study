package com.d_c3p0;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.a_domain.User;


public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void updata(User user) {
		String sql = "update t_user set username=?, password=? where id =?";
		Object[] args = {user.getUsername(),user.getPassword(),user.getId()};
		jdbcTemplate.update(sql,args);
	}

	// 查询所有
	public List<User> findAll() {
		return jdbcTemplate.query("select * from t_user",new Object[]{},  new BeanPropertyRowMapper<User>(User.class));
	}
	
	
}
