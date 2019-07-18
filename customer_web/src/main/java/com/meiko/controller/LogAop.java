package com.meiko.controller;

import com.meiko.domain.SysLog;
import com.meiko.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

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

    @Pointcut("execution(* com.meiko.controller.*.*(..))")
    public void pointCut1(){

    }
    @Before("pointCut1()")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime= new Date();
        methodName= joinPoint.getSignature().getName();
       /* Signature signature= joinPoint.getSignature();


        Class declaringType = signature.getDeclaringType();
        String declaringTypeName = signature.getDeclaringTypeName();
        String string = joinPoint.toLongString();
        String string1 = joinPoint.toShortString();
        String string2 = joinPoint.toString();
        Class<? extends JoinPoint> aClass = joinPoint.getClass();*/
        Object target = joinPoint.getTarget();
        clazz = target.getClass();
        url= request.getRequestURI();
//        Object[] args = joinPoint.getArgs();
//        if(args==null||args.length==0){
//            method = clazz.getMethod(methodName);
//        }else {
//            Class[] cArgs=new Class[args.length];
//            for(int i=0;i<cArgs.length;i++) {
//                cArgs[i]=args[i].getClass();
//            }
//            method=clazz.getMethod(methodName,cArgs);
//            }
//        RequestMapping annotationMethod = method.getAnnotation(RequestMapping.class);
//        String[] valueM = annotationMethod.value();
//        RequestMapping annotationClazz =  (RequestMapping)clazz.getAnnotation(RequestMapping.class);
//        String[] valueC = annotationClazz.value();
//        System.out.println(valueC[0]+valueM[0]);
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
