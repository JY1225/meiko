package com.meiko.service;


import java.util.List;

import com.meiko.domain.OFile;

public interface IFileService {
    List<OFile> findAllByUserId(Integer page,Integer pageSize,int userId,String fileName);
    
    List<OFile> findAll(Integer page,Integer pageSize,String fileName);
}
