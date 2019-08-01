package com.meiko.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.meiko.domain.FileLog;


public interface IFileLogDao {
	 @Select("select * from filelog order by loadTime desc ")
		List<FileLog> findAll();
	 
    @Insert("insert into filelog(cust_jccjs_list_id,file_name,type,down_loads,previews,loadTime,login_name) values(#{cust_jccjs_list_id},#{file_name},#{type},#{down_loads},#{previews},#{loadTime},#{login_name})")
	void save(FileLog fileLog);
    
	 @Select("select * from filelog where cust_jccjs_list_id=#{recid}")
	FileLog findOneByFileId(int recid);
	 
	@Select("select * from filelog where file_name LIKE #{file_name}")
	List<FileLog> findAllByFileName(String file_name);
}
