package com.meiko.controller;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import com.meiko.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;
    @RequestMapping("/findAll")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "3")Integer pageSize
                                )
    {
        List<UserInfo> list= service.findAll(page,pageSize);
        ModelAndView modelAndView=new ModelAndView();
        PageInfo<UserInfo> pageInfo=new PageInfo<>(list);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        service.save(userInfo);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
     UserInfo userInfo= service.findById(id);
     /*System.out.println(userInfo);
        List<Role> roles = userInfo.getRoles();
        for(Role role:roles){
            System.out.println(role);
            System.out.println(role.getPermissions());
        }
*/
     ModelAndView modelAndView=new ModelAndView();
     modelAndView.addObject("userInfo",userInfo);
     modelAndView.setViewName("user-show");
     return modelAndView;
    }
    @RequestMapping("/findNotRoles")
    public ModelAndView  findNotRoles(String id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("uid",id);
        List<Role> list= service.findNotRoles(id);
        modelAndView.addObject("roles",list);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }
    @RequestMapping("/saveUserRole")
    public String saveRole(String userId,String[] ids){
        for(String roleId :ids){
            service.saveUserRole(userId,roleId);
        }

        return "redirect:findAll";
    }
}
