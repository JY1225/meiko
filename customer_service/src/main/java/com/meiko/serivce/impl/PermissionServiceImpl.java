package com.meiko.serivce.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IPermissionDao;
import com.meiko.domain.Permission;
import com.meiko.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService{
    @Autowired
    private IPermissionDao dao;
    @Override
    public List<Permission> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }

    @Override
    public void save(Permission permission) {
        dao.save(permission);
    }

    @Override
    public Permission findOneById(String id) {
        return dao.findOneById(id);
    }
}
