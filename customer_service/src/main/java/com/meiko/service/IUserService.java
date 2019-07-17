package com.meiko.service;

import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;

import java.util.List;

public interface IUserService {
    List<UserInfo> findAll(int page, int pageSize);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findNotRoles(String id);

    void saveUserRole(String userId, String roleId);
    
    UserInfo findByUserName(String UserName);
}
