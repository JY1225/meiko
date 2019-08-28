package com.meiko.serivce.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IFileDao;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Dir;
import com.meiko.service.IFileService;

@Service()
public  class FileServiceImpl implements IFileService {
    @Autowired
    private IFileDao dao;
//    @Override
//    public List<Dir> findAllByUserId(Integer page,Integer pageSize,int userId,String fileName) {
//    	PageHelper.startPage(page,pageSize);
//   	  return dao.findAllByUserId(userId);
//    /*if(StringUtils.isBlank(fileName)) {
//    	PageHelper.startPage(page,pageSize);
//    	 return dao.findAllByUserId(userId);
//    }else {
//    	PageHelper.startPage(page,pageSize);
//    	 return dao.findAllByUserIdAndFileName(userId,fileName);
//    }*/
//       
//    }

    @Override
    public List<Dir> findAll(Integer page,Integer pageSize,String fileName) {
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

	@Override
	public void updateDirStausById(int status,String editUser, int id) {
		dao.updateDirStausById(status,editUser, id);
		
	}

	@Override
	public void deleteDirStausById(int id) {
		dao.deleteDirStausById(id);
		
	}

	@Override
	public void save(Dir dir) {
		dao.save(dir);
		
	}

	@Override
	public Dir findDirByStatus(int status) {		
		return dao.findDirByStatus(status);
	}

	@Override
	public void updateDownloads(int recid) {
		// TODO Auto-generated method stub
		dao.updateDownloads(recid);
	}

	@Override
	public void updatePreviews(int recid) {
		// TODO Auto-generated method stub
		dao.Previews(recid);
	}

	@Override
	public Cust_jccjs_list findByOne(int recid) {
		// TODO Auto-generated method stub
		return dao.findByOne(recid);
	}

	@Override
	public List<Cust_jccjs_list> findOneByName(String fileName) {
		// TODO Auto-generated method stub
		return dao.findOneByName(fileName);
	}
}
