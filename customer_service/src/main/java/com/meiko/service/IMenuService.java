package com.meiko.service;

import com.meiko.domain.Menu;


import java.util.List;

public interface IMenuService {
    List<Menu> findAll(int page, int pageSize);

    void save(Menu permission);

  /*  Menu findOneById(String id);*/
}
