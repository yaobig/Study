package com.itheima.g_annotation.b_web.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itheima.g_annotation.b_web.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	@Autowired
	@Qualifier("studentDaoId")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	public void addStudent() {
		studentDao.save();
	}
	
	
}
