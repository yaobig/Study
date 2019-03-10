package com.itheima.g_annotation.b_web.impl;

import org.springframework.stereotype.Repository;

@Repository("studentDaoId")
public class StudentDaoImpl implements StudentDao {

	@Override
	public void save() {
		System.out.println("dao");
	}

}
