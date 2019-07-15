package com.meiko.service;

import com.meiko.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(int page, int pageSize);

    void save(Permission permission);

    Permission findOneById(String id);
}
