package com.meiko.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IUserDao;
import com.meiko.domain.Cust_Addr;
import com.meiko.domain.Cust_jccjs_list;
import com.meiko.domain.LoginLog;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import com.meiko.service.ILoginLogService;
import com.meiko.service.IRoleService;
import com.meiko.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService ,UserDetailsService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private IUserDao dao;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ILoginLogService service;
    @Override
    public UserDetails loadUserByUsername(String s) {
    	 LoginLog loginLog=new LoginLog();
    	 loginLog.setIp(request.getRemoteAddr());
    	 loginLog.setLoginName(s);
    	 loginLog.setLoginTime(new Date());
    	 HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    	try {
    		UserInfo userInfo= dao.findByUserName(s);
    		User user = new User(userInfo.getUserName(),userInfo.getPassword(),getAuthority(userInfo.getRoles()));
    		
            if(s.equals(userInfo.getUserName()) && request.getParameter("password").equals(userInfo.getPassword()) 
            		&& userInfo.getStatus()==1) {
            	loginLog.setPassword(userInfo.getPassword());
                loginLog.setLoginStatus("OK");
                loginLog.setUserId(userInfo.getId());
                session.setAttribute("company", userInfo.getCompany());
            }else {
            	if(userInfo!=null) {
        			loginLog.setUserId(userInfo.getId());       			
        		}
            	String password = request.getParameter("password");
       		 	loginLog.setPassword(password);
       		 	loginLog.setLoginStatus("NG");     		 
            }       
            service.save(loginLog);
            return user;
    	}catch(Exception e) {
    		String password = request.getParameter("password");
   		 	loginLog.setPassword(password);
   		 	loginLog.setLoginStatus("NG");  
   		 	service.save(loginLog);
    	}
    	return null;
               	
    	}
    	
        


    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authority=new ArrayList<SimpleGrantedAuthority>();
        //HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
       
        if(roles != null) {
        for (Role role:roles){
            authority.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            //session.setAttribute("role", "ROLE_"+role.getName());
        }
        }
        return authority;

    }

    @Override
    public List<UserInfo> findAll(int page, int pageSize,String userName) {
    	List<UserInfo> list = new ArrayList<UserInfo>();
        if(StringUtils.isBlank(userName)) {
        	PageHelper.startPage(page,pageSize);
        	list = dao.findAll();
        	for(int i = 0;i < list.size();i++) {
        		list.get(i).setRoles(dao.findAllHasRole(list.get(i).getUserName()).getRoles());
        	}
        	return list;
        }else {
        	PageHelper.startPage(page,pageSize);
        	list = dao.findAllByName("%"+userName+"%");
        	for(int i = 0;i < list.size();i++) {
        		list.get(i).setRoles(dao.findAllHasRole(list.get(i).getUserName()).getRoles());
        	}
        	return list;
        }
        
    }

    @Override
    public int save(UserInfo userInfo) {
      /*userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));*/      
      if(dao.findAllByName(userInfo.getUserName()).size()>0) {
    	  JOptionPane.showMessageDialog(null,"用户名已存在","",JOptionPane.PLAIN_MESSAGE);
    	  return 0;
      }else {
    	  return dao.save(userInfo);
      }
	
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
		
        return dao.findByUserName(UserName);        
	}

	@Override
	public List<Cust_Addr> findNotFile(String id) {
		// TODO Auto-generated method stub
		return  dao.findNotFile(id);
	}

	@Override
	public void saveUserFile(String userId, String addr_id) {
		// TODO Auto-generated method stub
		
        dao.saveUserFile(userId,addr_id);
        
	}

	@Override
	public List<Cust_jccjs_list> findFiles(int page, int pageSize,int id,String fileName) {
		if(StringUtils.isBlank(fileName)) {
        	PageHelper.startPage(page,pageSize);
        	return dao.findFiles(id);
        }else {
        	PageHelper.startPage(page,pageSize);
        	return dao.findFilesByFileName("%"+fileName+"%");
        }		
	}

	@Override
	public void updateUserStausById(int id, int status) {
		dao.updateUserStausById(id,status);		
	}

	@Override
	public void passUpadateByName(String userName, String password) {
		try {
			dao.passUpadateByName(userName,password);
			JOptionPane.showMessageDialog(null,"密码修改成功","",JOptionPane.PLAIN_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"密码修改失败","",JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
		}
		
	}
}
