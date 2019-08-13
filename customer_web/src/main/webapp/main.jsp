<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/14
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>成绩书系统</title>
    <meta name="description" content="MEIKO">
    <meta name="keywords" content="MEIKO">

    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet"
          href="./plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="./plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="./plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="./plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="./plugins/morris/morris.css">
    <link rel="stylesheet"
          href="./plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="./plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="./plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="./plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="./plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="./plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="./plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="./plugins/select2/select2.css">
    <link rel="stylesheet"
          href="./plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="./plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="./plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="./plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="./css/style.css">
    <link rel="stylesheet"
          href="./plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="./plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="./plugins/bootstrap-slider/slider.css">
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
            width: 5%;
            height: 40px;
        }
        .case .part1 img {
            width: 30px;
            height: 30px;
            float: right;
            margin-top: 5px;
        }
        .case .part2 {
            float: left;
            width: 93%;
            height: 100px;
            text-indent: 1em;
            overflow: hidden;
        }
        #part2 ul {
            width: 100%;
            height: auto;
            list-style: none;
            padding: 0;
            margin: 0;
        }
        #part2 ul li {
            width: 100%;
            height: 30px;
            font-size: 16px;
            line-height: 30px;
            color: #DC143C;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
         
    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
   <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="${pageContext.request.contextPath}/aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">
        <!-- <img src="./images/center.jpg" width="100%" height="100%"/>  -->
        <!-- <img src="../images/bg.jpg" width="100%" height="100%"/>  -->
        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
               系统公告！
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/main.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/file/findAll">成绩书管理</a></li>
                <li class="active"></li>
            </ol>
        </section>
        <!-- 内容头部 /-->
        
            <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div   class="box-header with-border">
                   <p>
                                     欢迎登陆成绩书管理系统
                   </p>
				

                </div>
                <!-- /.error-content -->
            </div>
            <!-- 公告 -->
            <div class="case">
        <div class="part1">
            <img src="../img/notice.png"/>
        </div>
        <div class="part2" id="part2">
            <div id="scroll1">
                <ul>
                    <li><a href="#">1.成绩书支持在线预览，预览需要时间转换格式，根据文件大小而定，请稍微等候。</a></li>
                    <li><a href="#">2.成绩书在线下载，支持多选。</a></li> 
                    <li><a href="#">3.成绩书系统重视、保护客户隐私，客户和成绩书资源形成一一对应关系，每位客户只能够查看和操作自己的成绩书，无法获取不是自己的一切资源。</a></li>          
                </ul>
            </div>
            <div id="scroll2">
           
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

<script
        src="./plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="./plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script
        src="./plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="./plugins/raphael/raphael-min.js"></script>
<script
        src="./plugins/morris/morris.min.js"></script>
<script
        src="./plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="./plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="./plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="./plugins/knob/jquery.knob.js"></script>
<script
        src="./plugins/daterangepicker/moment.min.js"></script>
<script
        src="./plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="./plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="./plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="./plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="./plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="./plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="./plugins/fastclick/fastclick.js"></script>
<script
        src="./plugins/iCheck/icheck.min.js"></script>
<script
        src="./plugins/adminLTE/js/app.min.js"></script>
<script
        src="./plugins/treeTable/jquery.treetable.js"></script>
<script
        src="./plugins/select2/select2.full.min.js"></script>
<script
        src="./plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="./plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="./plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="./plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="./plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="./plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="./plugins/ckeditor/ckeditor.js"></script>
<script
        src="./plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="./plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="./plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="./plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="./plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="./plugins/chartjs/Chart.min.js"></script>
<script
        src="./plugins/flot/jquery.flot.min.js"></script>
<script
        src="./plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="./plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="./plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="./plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="./plugins/bootstrap-slider/bootstrap-slider.js"></script>
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
    
    //滚动

        var PartArea = document.getElementById('part2');
        var Scroll1 = document.getElementById('scroll1');
        var Scroll2 = document.getElementById('scroll2');

        Scroll2.innerHTML = Scroll1.innerHTML;

        function roll() {
            if(Scroll2.offsetHeight - PartArea.scrollTop <= 0) {
                PartArea.scrollTop -= Scroll1.offsetHeight;
            } else {
                PartArea.scrollTop++;
            }
        }

        var StopRoll = setInterval(roll, 60);

        PartArea.onmouseover = function () {
            clearInterval(StopRoll);
        }
        PartArea.onmouseout = function () {
            StopRoll = setInterval(roll, 60);
        }

    </script>

</body>

</html>