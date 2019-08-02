<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/14
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="main-sidebar" >
    <section class="sidebar"  >
        <div class="user-panel" style="height: 60px;" >
            <div class="pull-left image">
                <img src="../img/user.png" class="img-circle" alt="User Image">
            </div> 
            <div class="pull-left info" >
               <security:authentication property="principal.username"></security:authentication>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/main.jsp">
                    <i class="fa fa-dashboard"></i> <span>首页</span>
                </a>
            </li>
        

      
            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>基础数据</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/file/findAll">
                            <i class="fa fa-circle-o"></i> 成绩书管理
                        </a>
                    </li>
                    <%-- <li>
                        <a href="${pageContext.request.contextPath}/orders/findAll?page=1&pageSize=3">
                            <i class="fa fa-circle-o"></i> xxxx
                        </a>
                    </li> --%>
                </ul>
            </li>
            
           <%-- <c:if test="${sessionScope.role == 'ROLE_ADMIN' }">  --%>
 <li class="treeview" ><!-- id="role" style="visibility:hidden; -->
 <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="#">
                    <i class="fa fa-cogs"></i>
                    <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li>
                        <a href="${pageContext.request.contextPath}/user/findAll">
                            <i class="fa fa-circle-o"></i> 用户管理
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/role/findAll">
                            <i class="fa fa-circle-o"></i> 角色管理
                        </a>
                    </li>
                    <%-- <li>
                        <a href="${pageContext.request.contextPath}/permission/findAll">
                            <i class="fa fa-circle-o"></i> 资源权限管理
                        </a>
                    </li> --%>
                   <%--  <li>
                        <a href="${pageContext.request.contextPath}/syslog/findAll">
                            <i class="fa fa-circle-o"></i> 访问日志
                        </a>
                    </li> --%>
                    <li>
                        <a href="${pageContext.request.contextPath}/loginlog/findAll">
                            <i class="fa fa-circle-o"></i> 登录日志
                        </a>
                    </li>
                      <li>
                        <a href="${pageContext.request.contextPath}/filelog/findAll">
                            <i class="fa fa-circle-o"></i> 下载日志
                        </a>
                    </li>
                     <%-- <li>
                        <a href="${pageContext.request.contextPath}/menu/findAll">
                            <i class="fa fa-circle-o"></i> 菜单管理
                        </a>
                    </li> --%>
                     <li>
                        <a href="${pageContext.request.contextPath}/file/findAllDir">
                            <i class="fa fa-circle-o"></i> 文件路径管理
                        </a>
                    </li>
                </ul>
                </security:authorize> 
            </li>
           <%-- </c:if>  --%>
        </ul>
    </section>
</aside>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
/* $(document).ready(function () {
	
    $.get("/user/getUserName",function(data){
    	//var roleName=data[0].name;
    	for(var i = 0;i < data.length; i++){
    		 if(data[i].name.toLowerCase()=="admin"){
     	    	$("#role").css("visibility","visible");
     	    	break;
    	}   	    
    	}  	
    	
    },"json"); 
}); */
</script>
