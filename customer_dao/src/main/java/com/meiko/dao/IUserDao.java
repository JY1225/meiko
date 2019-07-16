package com.meiko.dao;

import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from userinfo where userName=#{userName}")
    @Results(id="userRoleMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
            many =@Many(select = "com.meiko.dao.IRoleDao.findById")
            )
    })
    UserInfo findByUserName(String s);
    
    
    @Select("select * from userinfo")
    List<UserInfo> findAll();

    @Insert("insert into userinfo(username,password,email,phonenum,status)values(#{userName},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);
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
}
