package com.meiko.service;

import java.util.List;

import com.meiko.domain.FileLog;

public interface IFileLogService {

	List<FileLog> findAll(Integer page, Integer pageSize, String loginName);

	void save(FileLog fileLog);

	FileLog findOneByFileId(int recid);

}
