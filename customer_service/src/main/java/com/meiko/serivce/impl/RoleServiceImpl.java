package com.meiko.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IRoleDao;
import com.meiko.domain.Menu;
import com.meiko.domain.Permission;
import com.meiko.domain.Role;
import com.meiko.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao dao;
    @Override
    public List<Role> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return   dao.findAll();
    }

    @Override
    public Role findOneById(String id) {
        return dao.findOneById(id);
    }

    @Override
    public void saveRolePermission(String id,String roleId) {
        dao.saveRolePermission(id,roleId);
    }
    @Override
    public void saveRoleMenu(String id,String roleId) {
        dao.saveRoleMenu(id,roleId);
    }

    @Override
    public List<Permission> findNotPermissions(String id) {
        return dao.findNotPermissions(id);
    }
    @Override
    public List<Menu> findNotMenus(String id) {
        return dao.findNotMenus(id);
    }

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		dao.save(role);
	}
}
