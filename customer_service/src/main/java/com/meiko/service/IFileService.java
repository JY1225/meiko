package com.meiko.service;


import java.util.List;

import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Dir;

public interface IFileService {
    //List<Dir> findAllByUserId(Integer page,Integer pageSize,int userId,String fileName);
    
    List<Dir> findAll(Integer page,Integer pageSize,String fileName);

	void updateDirStausById(int status, String name, int id);

	void deleteDirStausById(int id);

	void save(Dir dir);

	Dir findDirByStatus(int status);

	void updateDownloads(int recid);

	void updatePreviews(int recid);

	Cust_jccjs_list findByOne(int recid);

	Cust_jccjs_list findOneByName(String fileName);
}
