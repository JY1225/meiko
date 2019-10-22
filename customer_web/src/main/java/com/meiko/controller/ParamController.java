package com.meiko.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meiko.domain.Parameter;

@Controller
@RequestMapping("/param")
public class ParamController {
	
	@RequestMapping("/show")
	public ModelAndView findAll(
			@RequestParam(name = "flag", required = true) String flag,
			@RequestParam(name = "id", required = true) long id,
			@RequestParam(name = "rkey", required = true) long rkey) {
		ModelAndView modelAndView=new ModelAndView();
		Parameter p = new Parameter();
		p.setFlag(flag);
		p.setId(id);
		p.setRkey(rkey);
		Map<String, String> map = procedure(p);

		modelAndView.addObject("paramInfo",map);
        modelAndView.setViewName("param-show");
        return modelAndView;
	}

	public Map<String, String> procedure(Parameter p) {
		Connection ct = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Map<String, String> map = new HashMap<String, String>();
		try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            ct = DriverManager.getConnection("jdbc:oracle:thin:@192.1.8.248:1521:LIVE","admin","live");  
            //参数
            cs = ct.prepareCall("{call P_PRELEC_PARAME_COMMENT(?,?,?,?,?)}");  
            //以下三个都是in类型的参数  
            //给第一个参数赋值  
            cs.setString(1, p.getFlag());  
            //给第二个参数注册  
            cs.setLong(2, p.getId());  
            //给第三个参数注册  
            cs.setLong(3, p.getRkey());  
            //以下三个是out类型的参数，必须在java中进行注册  
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CHAR);  
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CHAR);              
            //执行存储过程  
            cs.execute();
            map.put("RESULT", cs.getString(4));
            map.put("RESULT_DESC", cs.getString(5));
            
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            //关闭资源  
        	try {
        	if(ct != null) {        		
        		ct.close();				 
        	}if(cs != null) {
        		cs.close();
        	}if(rs != null) {
        		rs.close();
        	}
        	}catch (SQLException e) {				
				e.printStackTrace();
			}
        	
        }
		return map;  
	}
}
