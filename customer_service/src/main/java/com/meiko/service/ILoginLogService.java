package com.meiko.service;

import java.util.List;

import com.meiko.domain.LoginLog;
import com.meiko.domain.SysLog;

public interface ILoginLogService {

	
	List<LoginLog> findAll(int page, int pageSize, String userName);

	void save(LoginLog loginLog);
}
