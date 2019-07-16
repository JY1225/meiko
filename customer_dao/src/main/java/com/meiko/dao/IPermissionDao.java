package com.meiko.dao;

import com.meiko.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission p,role_permission rp where p.id=rp.permissionid and rp.roleid=#{id}")//
    List<Permission> findById(String id);
   
    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(name,keyword) values(#{name},#{keyword})")
    void save(Permission permission);

    @Select("select * from permission where id=#{id}")
    @Results(id = "permissionRoleUser",value = {
            @Result(id = true,column = "id",property = "id"),
             @Result(column = "id",property = "roles",javaType = java.util.List.class,
            many = @Many(select = "com.meiko.dao.IRoleDao.findRolesById"))
    })
    Permission findOneById(String id);
}
