package com.meiko.service;

import java.util.List;

import com.meiko.domain.Cust_Addr;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;

public interface IUserService {
    List<UserInfo> findAll(int page, int pageSize,String userName);

    int save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findNotRoles(String id);

    void saveUserRole(String userId, String roleId);
    
    UserInfo findByUserName(String UserName);

	List<Cust_Addr> findNotFile(String id);
	
	List<Cust_jccjs_list> findFiles(int id, int pageSize, int i, String fileName, String toData);

	void saveUserFile(String userId, String fileId);

	void updateUserStausById(int id, int status);

	int passUpadateByName(String name, String password);

	int passUpadateById(int id, String password);

	int isUserNameExist(String userName);

	void deleUserFile(String userId);
}
