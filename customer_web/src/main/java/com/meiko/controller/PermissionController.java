package com.meiko.controller;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Permission;
import com.meiko.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;
    @RequestMapping("/findAll")
    public  String findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                           @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize,
                           Model model)
    {
        List<Permission> permissionList= service.findAll(page,pageSize);
        PageInfo<Permission> pageInfo=new PageInfo<Permission>(permissionList);
        model.addAttribute("pageInfo",pageInfo);
        return "permission-list";
    }
    @RequestMapping("/save")
    public String save(Permission permission){
        service.save(permission);
        return "redirect:/permission/findAll";
    }
    @RequestMapping("/findOneById")
    public ModelAndView findOneById(String id){
        ModelAndView modelAndView=new ModelAndView();
       Permission permission= service.findOneById(id);
       modelAndView.addObject("permission",permission);
       modelAndView.setViewName("permission-show");
        return modelAndView;
    }
   /* @RequestMapping("/savePermissionRoles")
    public String saveRoles(String id){
        return "permission-add"
    }*/
}
