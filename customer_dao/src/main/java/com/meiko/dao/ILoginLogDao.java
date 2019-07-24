package com.meiko.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.meiko.domain.LoginLog;

public interface ILoginLogDao {

	 @Select("select * from loginlog order by loginTime desc ")
	List<LoginLog> findAll();
	 @Insert("insert into loginlog(loginName,password,userId,loginStatus,loginTime,ip) values(#{loginName},#{password},#{userId},#{loginStatus},#{loginTime},#{ip})")
	void save(LoginLog loginLog);
	

}
