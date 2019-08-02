package com.meiko.serivce.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IFileLogDao;
import com.meiko.domain.FileLog;
import com.meiko.service.IFileLogService;
@Service
public class FileLogServiceImpl implements IFileLogService{
	 @Autowired
		private IFileLogDao dao;
	@Override
	public List<FileLog> findAll(Integer page, Integer pageSize, String file_name) {
		if(StringUtils.isBlank(file_name)) {
        	PageHelper.startPage(page,pageSize);
        	return dao.findAll();
        }else {
        	PageHelper.startPage(page,pageSize);
        	return dao.findAllByFileName("%"+file_name+"%");
        }
		
	}
	@Override
	public void save(FileLog fileLog) {
		// TODO Auto-generated method stub
		dao.save(fileLog);
	}
	@Override
	public FileLog findOneByFileId(int recid) {
		// TODO Auto-generated method stub
		return dao.findOneByFileId(recid);
	}

}
