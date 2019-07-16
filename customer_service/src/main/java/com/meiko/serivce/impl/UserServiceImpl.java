package com.meiko.serivce.impl;

import com.github.pagehelper.PageHelper;
import com.meiko.dao.IUserDao;
import com.meiko.domain.Role;
import com.meiko.domain.UserInfo;
import com.meiko.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService ,UserDetailsService {
    @Autowired
    private IUserDao dao;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo= dao.findByUserName(s);
        
        User  user = new User(userInfo.getUserName(),userInfo.getPassword(),getAuthority(userInfo.getRoles()));        
        System.out.println(user.getAuthorities());
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authority=new ArrayList<SimpleGrantedAuthority>();
       // authority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        for (Role role:roles){
            authority.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        return authority;

    }

    @Override
    public List<UserInfo> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return dao.findAll();
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
}
