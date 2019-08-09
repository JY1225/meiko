package com.meiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Menu;
import com.meiko.domain.Permission;
import com.meiko.domain.Role;
import com.meiko.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
   private IRoleService service;
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(name="pageSize",required = true,defaultValue = "3")Integer pageSize)
    {
      List<Role> roles=  service.findAll(page,pageSize);
        PageInfo<Role> pageInfo=new PageInfo<Role>(roles);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/findById")
    public  String findById(String id,Model model){
      Role role= service.findOneById(id);
      model.addAttribute("role",role);
      return "role-show";
    }
    
    @RequestMapping("/findByName")
    public  Role findByName(String name){
      Role role= service.findByName(name);
      return role;
    }
    
    @RequestMapping("/saveRolePermission")
    public String saveRolePermission(String roleId,String[] ids){
        for(String id:ids){
            service.saveRolePermission(id,roleId);
        }

         return "redirect:findAll";
    }
    
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(String roleId,String[] ids){
        for(String id:ids){
            service.saveRoleMenu(id,roleId);
        }

         return "redirect:findAll";
    }
   @RequestMapping("/findNotPermissions")
   public ModelAndView findNotPermissions(String id){
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.addObject("roleId",id);
       List<Permission> list=service.findNotPermissions(id);
       modelAndView.addObject("permissions",list);
       modelAndView.setViewName("role-permission-add");
       return modelAndView;
   }
   @RequestMapping("/findNotMenus")
   public ModelAndView findNotMenus(String id){
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.addObject("roleId",id);
       List<Menu> list=service.findNotMenus(id);
       modelAndView.addObject("menus",list);
       modelAndView.setViewName("role-menu-add");
       return modelAndView;
   }
   
   @RequestMapping("/save")
   public String save(Role role){
       service.save(role);
       return "redirect:findAll";
   }

}
