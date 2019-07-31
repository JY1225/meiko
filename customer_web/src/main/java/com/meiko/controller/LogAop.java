package com.meiko.controller;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.meiko.domain.SysLog;
import com.meiko.service.ISysLogService;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;
    private Long executionTime;
    private Method method;
    private String username;
    private String url;
    private String ip;
    private Class clazz;
    private String methodName;

	@Pointcut("execution(* com.meiko.controller111.*.*(..))")
    public void pointCut1(){

    }
    @Before("pointCut1()")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime= new Date();       
        methodName= joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        clazz = target.getClass();
        url= request.getRequestURI();
        ip=request.getRemoteAddr();
        SecurityContext context = SecurityContextHolder.getContext();
        username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
    }

    @AfterReturning("pointCut1()")
    public void doAfter(){
        executionTime=new Date().getTime()-visitTime.getTime();

      if(clazz!=SyslogController.class){
          SysLog sysLog=new SysLog();
          sysLog.setUrl(url);
          sysLog.setIp(ip);
          sysLog.setExecutionTime(executionTime);
          sysLog.setMethod(methodName);
          sysLog.setVisitTime(visitTime);
          sysLog.setUsername(username);
          sysLogService.save(sysLog);
      }


    }

}
