package com.meiko.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meiko.domain.Cust_jccjs_list;
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

    @Update("UPDATE dir  SET status=#{status},editUser=#{editUser} WHERE id = #{id}")
	void updateDirStausById(@Param("status") int status, @Param("editUser") String editUser, @Param("id") int id);

    @Delete("DELETE FROM dir  WHERE id = #{id}")
	void deleteDirStausById(int id);
    
    @Insert("insert into dir(editUser,url,status)values(#{editUser},#{url},#{status})")
	void save(Dir dir);

    @Select("SELECT * FROM dir WHERE status=#{status}")
	Dir findDirByStatus(@Param("status")int status);
    
    @Update("UPDATE cust_jccjs_list SET down_loads=down_loads+1 WHERE recid=#{recid}")
	void updateDownloads(@Param("recid")int recid);
    
    @Update("UPDATE cust_jccjs_list SET previews=previews+1 WHERE recid=#{recid}")
	void Previews( @Param("recid")int recid);
    @Select("select * from cust_jccjs_list where recid=#{recid}")
	Cust_jccjs_list findByOne(int recid);
}
