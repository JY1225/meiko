package com.meiko.service;

import com.meiko.domain.Menu;
import com.meiko.domain.Permission;
import com.meiko.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int page, int pageSize);

    Role findOneById(String id);

    void saveRolePermission(String id,String roleId);

    List<Permission> findNotPermissions(String id);
    
    List<Menu> findNotMenus(String id);

	void save(Role role);
}
