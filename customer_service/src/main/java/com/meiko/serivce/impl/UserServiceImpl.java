package com.meiko.serivce.impl;

import com.github.pagehelper.PageHelper;

import com.meiko.dao.IUserDao;
import com.meiko.domain.LoginLog;
import com.meiko.domain.OFile;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import com.meiko.service.ILoginLogService;
import com.meiko.service.ISysLogService;
import com.meiko.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("userService")
public class UserServiceImpl implements IUserService ,UserDetailsService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private IUserDao dao;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ILoginLogService service;
    
    @Override
    public UserDetails loadUserByUsername(String s)  {
    	 LoginLog loginLog=new LoginLog();
    	 loginLog.setIp(request.getRemoteAddr());
    	 loginLog.setLoginName(s);
    	 loginLog.setLoginTime(new Date());
    	 
    	 
    	try {
    		UserInfo userInfo= dao.findByUserName(s);   
            User  user = new User(userInfo.getUserName(),userInfo.getPassword(),getAuthority(userInfo.getRoles()));            
            //System.out.println(user.getAuthorities());
            
            loginLog.setPassword(userInfo.getPassword());
            loginLog.setLoginStatus("OK");
            loginLog.setUserId(userInfo.getId());
            service.save(loginLog);
            return user;
    	}
    	catch(Exception notFound) {
    		
    		LOG.debug("User '" + s + "' not found");  
    		 String password = request.getParameter("password");
    		 loginLog.setPassword(password);
    		 loginLog.setLoginStatus("NG");
    		 service.save(loginLog);
    		 //System.out.print(request.getParameter("password")+request.getRemoteAddr());
    	        throw notFound;  
    	   
    	}
        
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authority=new ArrayList<SimpleGrantedAuthority>();
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
       
        
        for (Role role:roles){
            authority.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            session.setAttribute("role", "ROLE_"+role.getName());
        }
        return authority;

    }

    @Override
    public List<UserInfo> findAll(int page, int pageSize,String userName) {
       
        if(StringUtils.isBlank(userName)) {
        	PageHelper.startPage(page,pageSize);
        	return dao.findAll();
        }else {
        	PageHelper.startPage(page,pageSize);
        	return dao.findAllByName(userName);
        }
        
    }

    @Override
    public void save(UserInfo userInfo) {
      userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
      dao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Role> findNotRoles(String id) {
        return dao.findNotRoles(id);
    }

    @Override
    public void saveUserRole(String userid, String roleid) {
    	int userId = Integer.parseInt(userid);
    	int roleId = Integer.parseInt(roleid);
        dao.saveUserRole(userId,roleId);
    }

	@Override
	public UserInfo findByUserName(String UserName) {
		// TODO Auto-generated method stub
		return dao.findByUserName(UserName);
	}

	@Override
	public List<OFile> findNotFile(String id) {
		// TODO Auto-generated method stub
		return  dao.findNotFile(id);
	}

	@Override
	public void saveUserFile(String userId, String fileId) {
		// TODO Auto-generated method stub
		
        dao.saveUserFile(userId,fileId);
	}
}
