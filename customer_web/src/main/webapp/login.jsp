<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/15
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"  
  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">  
 <meta http-equiv="X-UA-Compatible" content="ie=edge">  
<style type="text/css">
	.test{
		background-size:100% 100%;
		width:100%;
		height:100%;
		background-image:url(./images/bg.jpg);
		position:absolute;
		z-index:100;
	}

</style>
    <title>成绩书系统</title>

    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="./plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="./plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="./plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="./plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="./plugins/iCheck/square/blue.css">
</head>

<body class="test">  
<div class="login-box">
    <div class="login-logo">
        <a href="all-admin-index.html" class="achievement_system">成绩书管理系统</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg"><label class="login_title">登录系统</label></p>
<!-- <form>  
	<label>language</label>
     <select class="lan_select">  
         <option class="lan_zh">中文</option>  
         <option class="lan_en">英文</option>  
     </select>
 </form> -->
 
        <form action="./login" method="post">
            <div class="form-group has-feedback">
                <input type="text" name="username" class="form-control"
                       placeholder="username"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control"
                       placeholder="password"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <input type="checkbox"><label class="remember_me">记住下次自动登录</label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">
                    <font class="login">登录</font>
                    </button>
                </div>
                <!-- /.col -->
            </div>
        </form>

       <!--  <a href="#">忘记密码</a><br> -->


    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<script
        src="./plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="./plugins/iCheck/icheck.min.js"></script>
<script  src="./plugins/jQuery/jquery-3.4.1.min.js"></script>  
<script  src="./plugins/jQuery/jquery.i18n.properties.js"></script>  
<script  src="./plugins/jQuery/language.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass : 'icheckbox_square-blue',
            radioClass : 'iradio_square-blue',
            increaseArea : '20%' // optional
        });
    });
</script>
</body>

</html>
