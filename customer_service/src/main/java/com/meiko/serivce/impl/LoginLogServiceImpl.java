package com.meiko.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.ILoginLogDao;
import com.meiko.domain.LoginLog;
import com.meiko.service.ILoginLogService;

@Service
public class LoginLogServiceImpl implements ILoginLogService  {

	 @Autowired
	private ILoginLogDao dao;
	@Override
	public List<LoginLog> findAll(int page, int pageSize) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(page,pageSize);
		return dao.findAll();
	}
	@Override
	public void save(LoginLog loginLog) {
		// TODO Auto-generated method stub
		dao.save(loginLog);
	}

}
