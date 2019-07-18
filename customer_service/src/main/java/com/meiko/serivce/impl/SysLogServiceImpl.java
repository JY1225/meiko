package com.meiko.serivce.impl;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.ISysLogDao;
import com.meiko.domain.SysLog;
import com.meiko.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao dao;
    @Override
    public void save(SysLog sysLog) {
        dao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
    }
}
