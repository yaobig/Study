package com.service.impl;

import com.dao.AccountDao;
import com.service.AccountServcie;

public class AccountServcieImpl implements AccountServcie{
	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	@Override
	public void transfer(String outer, String inner, Integer money) {
		accountDao.in(inner, money);
		// 断点
		//int i = 1/0;
		
		accountDao.out(outer, money);
	}

}
