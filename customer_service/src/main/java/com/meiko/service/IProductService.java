package com.meiko.service;


import java.util.List;

import com.meiko.domain.Customer;

public interface IProductService {
    List<Customer> findAll(Integer page,Integer pageSize);
    

}
