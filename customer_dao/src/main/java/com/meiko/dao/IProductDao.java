package com.meiko.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.meiko.domain.Customer;

import java.util.List;

public interface IProductDao {
    @Select("select * from product")
    List<Customer>  findAll();
   
}
