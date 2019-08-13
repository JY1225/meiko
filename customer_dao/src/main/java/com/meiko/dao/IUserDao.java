package com.meiko.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.meiko.domain.Cust_Addr;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;

public interface IUserDao {

    @Select("select * from userinfo where userName=#{userName} and status = 1")
    @Results(id="userRoleMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
            many =@Many(select = "com.meiko.dao.IRoleDao.findById")
            )
    })
    UserInfo findByUserName(String s);
    
    @Select("select * from userinfo where userName=#{userName}")
    @Results(id="userRolesMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
            many =@Many(select = "com.meiko.dao.IRoleDao.findById")
            )
    })
    UserInfo findAllHasRole(String s);
    
    @Select("select * from userinfo")
    List<UserInfo> findAll();
    
    @Select("select * from userinfo where userName like #{userName}")
    List<UserInfo> findAllByName(String userName);
    
    @Insert("insert into userinfo(username,password,mark,status,company)values(#{userName},#{password},#{mark},#{status},#{company})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save(UserInfo userInfo);
    
    @Select("select * from userinfo where id=#{id}")
    @Results(id = "UserInfoRolePermission", value={
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,
            many = @Many(select = "com.meiko.dao.IRoleDao.findById")
            )
    }
    )
    UserInfo findById(String id);

    @Select("select * from userinfo u,userinfo_role ur where u.id=ur.userid and ur.roleid=#{id}")
    List<UserInfo> findUsersById(String id);
    @Select("select * from role r where r.id not in( select ur.roleid from userinfo_role ur where ur.userid=#{id})")
    List<Role> findNotRoles(String id);
    
    @Insert("insert into userinfo_role(userId,roleId) values (#{userId},#{roleId})")
    void saveUserRole(@Param("userId") int userId,@Param("roleId") int roleId);

    @Select("select * from cust_jccjs_list t where t.cust_addr_id in( select ur.addr_id from userinfo_cust_addr ur where ur.user_id=#{id})")
	List<Cust_jccjs_list> findFiles(int id);
	
	@Select("select * from cust_jccjs_list t where t.cust_addr_id in( select ur.addr_id from userinfo_cust_addr ur where ur.user_id=#{id}) AND CONVERT(varchar(100), t.shipping_dt, 23) BETWEEN #{fromData} AND  #{toData}")
	List<Cust_jccjs_list> findFilesByData(@Param("id")int id,@Param("fromData")String fromData, @Param("toData")String toData);

	@Select("select * from cust_jccjs_list t where t.cust_addr_id in( select ur.addr_id from userinfo_cust_addr ur where ur.user_id=#{id}) AND CONVERT(varchar(100), t.shipping_dt, 23) >= #{fromData}")
	List<Cust_jccjs_list> findFilesByFromData(@Param("id")int id,@Param("fromData")String fromData);

	@Select("select * from cust_jccjs_list t where t.cust_addr_id in( select ur.addr_id from userinfo_cust_addr ur where ur.user_id=#{id}) AND CONVERT(varchar(100), t.shipping_dt, 23) <= #{toData}")
	List<Cust_jccjs_list> findFilesByTodata(@Param("id")int id,@Param("toData")String toData);
	
    @Select("select * from CUST_ADDR t where t.recid not in( select ur.addr_id from userinfo_cust_addr ur where ur.user_id=#{id})")
	List<Cust_Addr> findNotFile(String id);

    //@Insert("insert into user_file(userId,fileId) values (#{userId},#{fileId})")
    @Insert("insert into userinfo_cust_addr(user_id,addr_id) values (#{userId},#{addr_id})")
	void saveUserFile(@Param("userId")String userId,@Param("addr_id")String addr_id);

    @Select("select * from cust_jccjs_list t where t.upload_filename like #{fileName}")
	List<Cust_jccjs_list> findFilesByFileName(String fileName);

	@Update("UPDATE userinfo  SET status=#{status} WHERE id = #{id}")
	void updateUserStausById(@Param("id")int id, @Param("status")int status);

	@Update("UPDATE userinfo  SET password=#{password} WHERE userName = #{userName}")
	void passUpadateByName(@Param("userName")String userName, @Param("password")String password);
	
	@Update("UPDATE userinfo  SET password=#{password} WHERE id = #{id}")
	void passUpadateById(@Param("id")int id, @Param("password")String password);
	
	
}
