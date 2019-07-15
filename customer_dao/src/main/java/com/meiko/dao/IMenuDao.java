package com.meiko.dao;


import org.apache.ibatis.annotations.*;

import com.meiko.domain.Menu;

import java.util.List;

public interface IMenuDao {
    /*@Select("select * from permission p,role_permission rp where p.id=rp.permissionid and rp.roleid=#{id} ")
    List<Menu> findById(String id);*/
    @Select("select * from menu")
    List<Menu> findAll();

    @Insert("insert into menu(name,parentId,level,url,state,remarks) values(#{name},#{parentId},#{level},#{url},#{state},#{remarks})")
    void save(Menu Menu);

    @Select("select * from permission where id=#{id}")
    @Results(id = "permissionRoleUser",value = {
            @Result(id = true,column = "id",property = "id"),
             @Result(column = "id",property = "roles",javaType = java.util.List.class,
            many = @Many(select = "com.meiko.dao.IRoleDao.findRolesById"))
    })
    Menu findOneById(String id);
}
