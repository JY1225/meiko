package com.meiko.serivce.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IMenuDao;
import com.meiko.domain.Menu;
import com.meiko.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{
    @Autowired
    private IMenuDao dao;
    @Override
    public List<Menu> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }

    @Override
    public void save(Menu permission) {
        dao.save(permission);
    }
    

    /*@Override
    public Permission findOneById(String id) {
        return dao.findOneById(id);
    }*/
}
