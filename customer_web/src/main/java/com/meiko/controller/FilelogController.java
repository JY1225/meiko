package com.meiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.FileLog;

import com.meiko.service.IFileLogService;


@Controller
@RequestMapping("/filelog")
public class FilelogController {
	@Autowired
    private IFileLogService service;
    @RequestMapping("/findAll")
    public  String findAll(
            @RequestParam(name="page",required = true,defaultValue = "1")Integer page,
            @RequestParam(name="pageSize",required = true,defaultValue = "3")Integer pageSize,
            @RequestParam(name="file_name",required=false) String file_name,
            Model model)
    {
     List<FileLog> list= service.findAll(page,pageSize,file_name);
        PageInfo<FileLog> pageInfo=new PageInfo<FileLog>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "filelog-list";
    }
}
