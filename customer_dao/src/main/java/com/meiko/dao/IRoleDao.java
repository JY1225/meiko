package com.meiko.dao;

import com.meiko.domain.Menu;
import com.meiko.domain.Permission;
import com.meiko.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role r,userinfo_role u_r where r.id=u_r.roleid and u_r.userid=#{id}")//
    @Results(id = "UserolePermission",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions",javaType = java.util.Set.class,
           many = @Many(select = "com.meiko.dao.IPermissionDao.findById")
           )
    })
    public List<Role> findById(String id);

    @Select("select * from role")
    List<Role> findAll();


    @Select("select * from role where id=#{id}")
    @Results(id = "rolePermission",value = {
            @Result(id = true,property = "id", column = "id"),
            @Result(column = "id",property = "permissions",javaType = java.util.List.class,
            many = @Many(select = "com.meiko.dao.IPermissionDao.findById"))
    })
    Role findOneById(String id);

    @Select("select * from role r,role_permission rp where r.id=rp.roleid and rp.permissionid=#{id}")
    @Results(id = "roleUser",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(column = "id",property = "users",javaType = java.util.List.class,
            many = @Many(select = "com.meiko.dao.IUserDao.findUsersById")
            )
    })
    public List<Role> findRolesById(String id);
    
    @Insert("insert into role_permission(permissionid,roleid) values(#{id},#{roleId})")
    void saveRolePermission(@Param("id") String id,@Param("roleId") String roleId);
    
    @Insert("insert into role_menu(menuid,roleid) values(#{id},#{roleId})")
    public void saveRoleMenu(@Param("id") String id,@Param("roleId") String roleId);
    
    @Select("select * from permission p where p.id not in( select rp.permissionid from role_permission rp where rp.roleid=#{id})")
    List<Permission> findNotPermissions(String id);
    
    @Select("select * from menu m where m.id not in( select rm.menuid from role_menu rm where rm.roleid=#{id})")
    List<Menu> findNotMenus(String id);
    
    @Insert("insert into role(name,description)values(#{name},#{description})")
	public void save(Role role);
    @Select("select * from role where name=#{name}")
	public Role findByName(String name);

	
	
}
