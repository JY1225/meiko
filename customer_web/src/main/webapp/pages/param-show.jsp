<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title><%-- <spring:message code="achievement_system"/> --%></title>
    <meta name="description" content="MEIKO">
    <meta name="keywords" content="MEIKO">

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet"
          href="../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="../plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="../plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="../plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="../plugins/morris/morris.css">
    <link rel="stylesheet"
          href="../plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="../plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="../plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="../plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="../plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="../plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="../plugins/select2/select2.css">
    <link rel="stylesheet"
          href="../plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="../plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="../css/style.css">
    <link rel="stylesheet"
          href="../plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="../plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="../plugins/bootstrap-slider/slider.css">
   <style type="text/css">
        .case {
            position: absolute;
            width: 100%;
            height: 100px;
            overflow: hidden;
            left: calc(40% - 400px);
            top: 200px;
        }
        .case .part1 {
            float: left;
            width: 15%;
            height: 40px;
        }
        .case .part1 img {
            width: 30px;
            height: 30px;
            float: right;
            margin-top: 0px;
        }
        .case .part2 {
            float: left;
            width: 75%;
            height: 50px;
        }

         
    </style>
    
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
   <%-- <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include> --%>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <%-- <jsp:include page="${pageContext.request.contextPath}/aside.jsp"></jsp:include> --%>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">       
            <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div   class="box-header with-border">
                   <p class="welcome_system">
                       <!--  欢迎登陆成绩书管理系统 -->
                       <%-- <spring:message code="welcome_system"/>   --%>
                   </p>				
                </div>
                <!-- /.error-content -->
            </div>
            <!-- 公告 -->
            <div class="case">
        <div class="part1">
            <!-- <img src="../img/notice.png"/> -->
        </div>
        <div class="part2" id="part2">
            <div id="scroll1">
                
                    <a href="#">RESULT:  &nbsp; ${paramInfo.RESULT };</a><br><br>
                    <a href="#">RESULT_DESC:  &nbsp; ${paramInfo.RESULT_DESC };</a> <br>                  
                
            </div>
        </div>
    </div>
    <!-- /.error-page -->
        </section>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
    <!-- 底部导航 /-->
</div>
<script>
    $(document).ready(function () {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale: 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document).ready(function () {
        // 激活导航位置
        setSidebarActive("admin-index");
    });

    </script>

</body>

</html>
