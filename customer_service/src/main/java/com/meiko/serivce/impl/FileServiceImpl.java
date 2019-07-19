package com.meiko.serivce.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
    public List<OFile> findAllByUserId(Integer page,Integer pageSize,int userId,String fileName) {
    	PageHelper.startPage(page,pageSize);
   	  return dao.findAllByUserId(userId);
    /*if(StringUtils.isBlank(fileName)) {
    	PageHelper.startPage(page,pageSize);
    	 return dao.findAllByUserId(userId);
    }else {
    	PageHelper.startPage(page,pageSize);
    	 return dao.findAllByUserIdAndFileName(userId,fileName);
    }*/
       
    }

    @Override
    public List<OFile> findAll(Integer page,Integer pageSize,String fileName) {
    	PageHelper.startPage(page,pageSize);
   	 	return dao.findAll();
    	/*if(StringUtils.isBlank(fileName)) {
        	PageHelper.startPage(page,pageSize);
        	 return dao.findAll();
        }else {
        	PageHelper.startPage(page,pageSize);
        	 return dao.findAllbyFileName(fileName);
        }*/
    }
}
