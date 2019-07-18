package com.meiko.service;

import com.meiko.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ISysLogService {
    @Insert(" insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    List<SysLog> findAll(int page, int pageSize);
}
