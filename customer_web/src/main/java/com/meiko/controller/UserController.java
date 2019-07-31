package com.meiko.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.meiko.domain.Cust_Addr;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import com.meiko.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

   
   /* @Secured({"ROLE_ADMIN","ROLE_USER"})*/
    
    @RequestMapping("/getUserName")
    
    public @ResponseBody List<Role> getUserName() {
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();
    //	SecurityContextHolder.getContext().getAuthentication().getCredentials();
    	System.out.println(name);
    	ModelAndView modelAndView=new ModelAndView();
      
    	UserInfo userInfo = service.findByUserName(name);
    	List<Role> list = userInfo.getRoles();
    	
    	return list;
    }
    
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "3")Integer pageSize,
                                @RequestParam(name="userName",required=false) String userName
                                )
    {
    	
        List<UserInfo> list= service.findAll(page,pageSize,userName);
        ModelAndView modelAndView=new ModelAndView();
        PageInfo<UserInfo> pageInfo=new PageInfo<UserInfo>(list);
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
    
    @RequestMapping("/findNotFile")
    public ModelAndView  findNotFile(String id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("uid",id);
        List<Cust_Addr> list= service.findNotFile(id);
        modelAndView.addObject("files",list);
        modelAndView.setViewName("user-file-add");
        return modelAndView;
    }
    @RequestMapping("/saveUserRole")
    public String saveRole(String userId,String[] ids){
        for(String roleId :ids){
            service.saveUserRole(userId,roleId);
        }

        return "redirect:findAll";
    }
    @RequestMapping("/saveUserFile")
    public String saveFile(String userId,String[] ids){
        for(String addr_id :ids){
            service.saveUserFile(userId,addr_id);
        }

        return "redirect:findAll";
    }
    
    @RequestMapping("/userOnById")
    public String userOnById(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="id",required=false) int id) {    	
    	
    		service.updateUserStausById(id,1); 
    		   	  
        return   "redirect:findAll";
    }
    
    @RequestMapping("/userOffById")
    public String userOffById(Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "3") Integer pageSize,
            @RequestParam(name="id",required=false) int id) {
    	
    	service.updateUserStausById(id,0);        
        return  "redirect:findAll";
    }
    
    @RequestMapping("/passUpadateByName")
    private String passUpadateById(UserInfo userInfo){
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();                
        service.passUpadateByName(name,userInfo.getPassword());
        return  "password-edit";
    }
}
