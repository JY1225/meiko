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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<aside class="main-sidebar" >
    <section class="sidebar"  >
        <div class="user-panel" style="height: 60px;" >
            <div class="pull-left image">
                <img src="../img/user.png" class="img-circle" alt="User Image">
            </div> 
            <div class="pull-left info" >
               <security:authentication property="principal.username"></security:authentication>
                <a href="" ><i class="fa fa-circle text-success"></i>
                	<!-- <font class="online">在线</font> -->
                	<spring:message code="online"/></a>
            </div>
        </div>

        <ul class="sidebar-menu">
            <li class="header"><!-- <font class="menu">菜单</font> -->
            <spring:message code="menu"/>
            </li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/main.jsp">
                    <i class="fa fa-dashboard"></i> <!-- <span class="home_page">首页</span> -->
                    <spring:message code="home_page"/>
                </a>
            </li>
        

      
            <li class="treeview"><a href="${pageContext.request.contextPath}/file/findAll"> <i class="fa fa-cube"></i>
                <!-- <span class="report_list">成绩书管理</span>  -->
                <spring:message code="report_list"/>
                <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
            </li>
            
           <%-- <c:if test="${sessionScope.role == 'ROLE_ADMIN' }">  --%>
 <li class="treeview" ><!-- id="role" style="visibility:hidden; -->
 <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="#">
                    <i class="fa fa-cogs"></i>
                    <!-- <span class="system_manage">系统管理</span> -->
                    <spring:message code="system_manage"/>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li>
                        <a href="${pageContext.request.contextPath}/user/findAll">
                            <i class="fa fa-circle-o"></i><!-- <font class="user_manage">用户管理</font>  -->
                            <spring:message code="user_manage"/>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/role/findAll">
                            <i class="fa fa-circle-o"></i><!-- <font class="role_manage">角色管理</font>  -->
                            <spring:message code="role_manage"/>
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
                            <i class="fa fa-circle-o"></i> <!-- <font class="login_log">登录日志</font>  -->
                            <spring:message code="login_log"/>
                        </a>
                    </li>
                      <li>
                        <a href="${pageContext.request.contextPath}/filelog/findAll">
                            <i class="fa fa-circle-o"></i> <!-- <font class="download_log">下载日志</font> -->
                            <spring:message code="download_log"/>
                        </a>
                    </li>
                     <%-- <li>
                        <a href="${pageContext.request.contextPath}/menu/findAll">
                            <i class="fa fa-circle-o"></i> 菜单管理
                        </a>
                    </li> --%>
                     <li>
                        <a href="${pageContext.request.contextPath}/file/findAllDir">
                            <i class="fa fa-circle-o"></i> <!-- <font class="source_url">文件路径管理</font> -->
                            <spring:message code="source_url"/>
                        </a>
                    </li>
                    
                    <li>
                        <a href="${pageContext.request.contextPath}/param/show">
                            <i class="fa fa-circle-o"></i> <!-- <font class="source_url">文件路径管理</font> -->
                            <spring:message code="param_handle"/>
                        </a>
                    </li>
                </ul>
                </security:authorize> 
            </li>
        </ul>
    </section>
</aside>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script type="text/javascript">
function menuClick(url) {   	
    $.ajax({
        method:"post",
        url:url,
        data:{'id':id},
        success:function(exeDetail){           	
        	if(exeDetail != null && exeDetail != ""){
        		layer.alert(exeDetail, {
        		    skin: 'layui-layer-lan'
        		    ,closeBtn: 0
        		  });  
        	}else{
        		location=redirectUrl; 
        	}
        },
    });
}
</script>

