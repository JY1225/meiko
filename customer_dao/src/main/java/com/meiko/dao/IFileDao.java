package com.meiko.dao;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.meiko.domain.OFile;

public interface IFileDao {
    @Select("SELECT * FROM ofile WHERE id IN (SELECT uf.`fileId` fid FROM userinfo u INNER JOIN user_file uf ON u.id=uf.userId  AND u.`id`=#{UserId})")
    List<OFile>  findAllByUserId(int UserId);
   
    @Select("SELECT * FROM ofile")
    List<OFile>  findAll();
    
    @Select("SELECT * FROM ofile WHERE id IN (SELECT uf.`fileId` fid FROM userinfo u INNER JOIN user_file uf ON u.id=uf.userId  AND u.`id`=#{UserId}) AND fileName LIKE concat('%',#{fileName},'%') ")
	List<OFile> findAllByUserIdAndFileName(int userId, String fileName);

    @Select("SELECT * FROM ofile WHERE fileName LIKE concat('%',#{fileName},'%')")
	List<OFile> findAllbyFileName(String fileName);
}
