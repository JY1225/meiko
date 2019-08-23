package com.meiko.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.meiko.service.IRoleService;
import com.meiko.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;
    @Autowired
    private IRoleService roleService;
   
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
                                @RequestParam(name = "pageSize",required = true,defaultValue = "5")Integer pageSize,
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
    
    @RequestMapping("/isUserNameExist")
    @ResponseBody   
    public void isUserNameExist(@RequestParam(name="userName",required = false) String userName,
    		HttpServletResponse response){
    	response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
    	int id = service.isUserNameExist(userName);
    	try {
    	if(id !=0 ) {    		
			response.getWriter().print("× 用户名已注册");			
    	}else {
    		response.getWriter().print("√ 验证成功");
    	}
    	} catch (IOException e) {			
			e.printStackTrace();
		}
		//return id;         
    }
    
	@RequestMapping("/save")
    public String save(UserInfo userInfo){
        int id = service.save(userInfo);
        if(id != 0) {
        	Role role = roleService.findByName(userInfo.getRole());
        	service.saveUserRole(String.valueOf(userInfo.getId()),String.valueOf(role.getId()));
        }
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
    /**
     * 用户添加角色
     * @param userId
     * @param ids
     * @return
     */
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
    @ResponseBody //加此
    private String passUpadateByName(UserInfo userInfo){
    	String name = SecurityContextHolder.getContext().getAuthentication().getName();                
        int isUp = service.passUpadateByName(name,userInfo.getPassword());
        if(isUp == 1) {
    		return "√ 密码修改成功";
    	}else {
    		return "× 密码修改失败";
    	}    
       // return  "password-edit";
    }
    
    @RequestMapping("/passUpadateById")
    @ResponseBody  
    private String passUpadateById(UserInfo userInfo){    	
    	int isUp = service.passUpadateById(userInfo.getId(),userInfo.getPassword());
    	if(isUp == 1) {
    		return "√ 密码重置成功";
    	}else {
    		return "× 密码重置失败";
    	}    	
    }
}
