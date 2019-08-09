<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/14
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"></span> <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>${sessionScope.company}
        </b>成绩书系统</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas"
           role="button"> <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">

                <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
               <img src="../img/user.png" class="user-image" alt="User Image"> 
                 <span>
				    <security:authentication property="principal.username"></security:authentication>				    
				</span>            
                </a> 
             
                    <ul class="dropdown-menu" style="width:20px;">
                      

                        <!-- Menu Footer-->
                        <%-- <li class="user-footer" >
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">修改密码</a>
                            </div>
                            <div class="pull-right" >
                                <a href="${pageContext.request.contextPath}/logout"
                                   class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li> --%>
                    </ul></li>
                    <li  >                        
                         <a href="${pageContext.request.contextPath}/pages/password-edit.jsp" >修改密码</a>                   
                    </li>
					<li  >                                                      
                        <a href="${pageContext.request.contextPath}/logout">注销</a>
                            
                   </li>
            </ul>
        </div>
    </nav>
</header>
