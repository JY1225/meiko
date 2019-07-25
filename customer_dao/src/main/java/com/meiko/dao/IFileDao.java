package com.meiko.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meiko.domain.Dir;

public interface IFileDao {
//    @Select("SELECT * FROM ofile WHERE id IN (SELECT uf.`fileId` fid FROM userinfo u INNER JOIN user_file uf ON u.id=uf.userId  AND u.`id`=#{UserId})")
//    List<Dir>  findAllByUserId(int UserId);
   
    @Select("SELECT * FROM dir")
    List<Dir>  findAll();
    
//    @Select("SELECT * FROM ofile WHERE id IN (SELECT uf.`fileId` fid FROM userinfo u INNER JOIN user_file uf ON u.id=uf.userId  AND u.`id`=#{UserId}) AND fileName LIKE concat('%',#{fileName},'%') ")
//	List<Dir> findAllByUserIdAndFileName(int userId, String fileName);

//    @Select("SELECT * FROM ofile WHERE fileName LIKE concat('%',#{fileName},'%')")
//	List<Dir> findAllbyFileName(String fileName);

    @Update("UPDATE dir t SET t.status=#{status},t.editUser=#{editUser} WHERE t.id = #{id}")
	void updateDirStausById(@Param("status") int status, @Param("editUser") String editUser, @Param("id") int id);

    @Delete("DELETE FROM dir  WHERE id = #{id}")
	void deleteDirStausById(int id);
    
    @Insert("insert into dir(editUser,url,status)values(#{editUser},#{url},#{status})")
	void save(Dir dir);

    @Select("SELECT * FROM dir WHERE status=#{status}")
	Dir findDirByStatus(@Param("status")int status);
}
