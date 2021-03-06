package com.meiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.LoginLog;
import com.meiko.service.ILoginLogService;
@Controller
@RequestMapping("/loginlog")
public class LoginController {
	@Autowired
    private ILoginLogService service;
    @RequestMapping("/findAll")
    public  String findAll(
            @RequestParam(name="page",required = true,defaultValue = "1")Integer page,
            @RequestParam(name="pageSize",required = true,defaultValue = "10")Integer pageSize,
            @RequestParam(name="loginName",required=false) String loginName,
            Model model)
    {
     List<LoginLog> list= service.findAll(page,pageSize,loginName);
        PageInfo<LoginLog> pageInfo=new PageInfo<LoginLog>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "loginlog-list";
    }
}
