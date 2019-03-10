package com.f_properties;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.a_domain.User;


public class UserDao extends JdbcDaoSupport{
	

	
	public void updata(User user) {
		String sql = "update t_user set username=?, password=? where id =?";
		Object[] args = {user.getUsername(),user.getPassword(),user.getId()};
		this.getJdbcTemplate().update(sql,args);
	}

	// 查询所有
	public List<User> findAll() {
		return this.getJdbcTemplate().query("select * from t_user",new Object[]{},  new BeanPropertyRowMapper<User>(User.class));
	}

	public User getById(int id) {
		return this.getJdbcTemplate().queryForObject("select * from t_user where id =?",new BeanPropertyRowMapper<User>(User.class), id);
	}
	
	
}
