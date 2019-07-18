package com.meiko.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IFileDao;
import com.meiko.domain.OFile;
import com.meiko.service.IFileService;
@Service()
public  class FileServiceImpl implements IFileService {
    @Autowired
    private IFileDao dao;
    @Override
    public List<OFile> findAllByUserId(Integer page,Integer pageSize,int userId) {
        PageHelper.startPage(page,pageSize);
        return dao.findAllByUserId(userId);
    }

    @Override
    public List<OFile> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }
}
