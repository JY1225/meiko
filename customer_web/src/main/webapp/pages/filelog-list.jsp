<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/20
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>MEIKO-CUSTOMER</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

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
    <link rel="stylesheet"
          href="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
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

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
           下载日志<small>全部日志</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="../main.html"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a
                        href="../pages/filelog-list.html">下载日志</a></li>

                <li class="active">全部日志</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="刷新"
                                            onclick="window.location.reload();">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>
                         <div class="box-tools pull-right">
                            <div class="">
                            <form action="${pageContext.request.contextPath}/filelog/findAll" method="post" >
                                <div class="col-md-8"><input type="text" class="form-control input-sm" name="file_name"
                                       placeholder="账号">
                                 </div>   
                                 <div class="col-md-1">
                                         <button type="submit" class="btn btn-default">搜索</button> 
                                  </div>
                             </form>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px"><input id="selall"
                                                                               type="checkbox" class="icheckbox_square-blue"></th>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting">文件名</th>
                                <th class="sorting">文件id</th>
                                <th class="sorting">操作类型</th>
                                <th class="sorting">操作时间</th>
                                <th class="sorting">登录用户名</th>
                                <th class="sorting">下载总数</th>
                                <th class="sorting">预览总数</th>
                                 
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="filelog">
                                <tr>
                                    <td><input name="ids" type="checkbox"></td>
                                    <td>${filelog.recid}</td>
                                    <td>${filelog.file_name}</td>
                                    <td>${filelog.cust_jccjs_list_id}</td>
                                    <td>${filelog.type}</td>
                                    <td>${filelog.loadTime}</td>
                                    <td>${filelog.login_name}</td>
                                    <td>${filelog.down_loads}</td>
                                    <td>${filelog.previews}</td>
                                    
                                </tr>
                            </c:forEach>



                            </tbody>

                        </table>
                        <!--数据列表/-->

                        <!--工具栏-->
                        <!-- <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="刷新"
                                            onclick="window.location.reload();">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm"
                                       placeholder="搜索"> <span
                                    class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div> -->
                        <!--工具栏/-->


                    </div>
                    <!-- 数据表格 /-->

                </div>
                <!-- /.box-body -->

                <!-- .box-footer-->
                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共${pageInfo.pages}页，共${pageInfo.total} 条数据。 每页 <select class="form-control" id="changePageSize">
                            <option  <c:if test="${pageInfo.pageSize==1}">selected</c:if>>1 </option>
                            <option  <c:if test="${pageInfo.pageSize==2}">selected</c:if>>2</option>
                            <option  <c:if test="${pageInfo.pageSize==3}">selected</c:if>>3 </option>
                            <option  <c:if test="${pageInfo.pageSize==4}">selected</c:if>>4</option>
                            <option  <c:if test="${pageInfo.pageSize==5}">selected</c:if>>5</option>
                        </select> 条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/filelog/findAll?page=1&pageSize=${pageInfo.pageSize}" aria-label="Previous" aria-label="Previous">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/filelog/findAll?page=${pageInfo.pageNum-1}&pageSize=${pageInfo.pageSize}" aria-label="Previous">上一页</a></li>
                            <c:if test="${pageInfo.pages<10 }">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="${pageInfo.pages}"></c:set>
                            </c:if>
                            <c:if test="${pageInfo.pages==10 }">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="${pageInfo.pages}"></c:set>
                            </c:if>
                            <c:if test="${pageInfo.pages>10}">
                                <c:set var="begin" value="${pageInfo.pageNum-5}"></c:set>
                                <c:set var="end" value="${pageInfo.pageNum+4}"></c:set>
                                <c:if test="${begin<0}">
                                    <c:set var="begin" value="1"></c:set>
                                    <c:set var="end" value="${begin+9}"></c:set>
                                </c:if>
                                <c:if test="${end>pageInfo.pages}">
                                    <c:set var="end" value="${pageInfo.pages}"></c:set>
                                    <c:set var="begin" value="${end-9}"></c:set>

                                </c:if>
                            </c:if>


 							<c:forEach begin="${begin}" end="${end}" var="i">
                                <li><a href="${pageContext.request.contextPath}/filelog/findAll?page=${i}&pageSize=${pageInfo.pageSize}">${i}</a></li>
                            </c:forEach>


                            <li><a href="${pageContext.request.contextPath}/filelog/findAll?page=${pageInfo.pageNum+1}&pageSize=${pageInfo.pageSize}">下一页</a></li>
                            <li><a href="${pageContext.request.contextPath}/filelog/findAll?page=${pageInfo.pages}&pageSize=${pageInfo.pageSize}" aria-label="Next">尾页</a></li>
                        </ul>
                    </div>

                </div>
                <!-- /.box-footer-->

            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
   <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
    <!-- 底部导航 /-->

</div>

<script
        src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="../plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script
        src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="../plugins/raphael/raphael-min.js"></script>
<script
        src="../plugins/morris/morris.min.js"></script>
<script
        src="../plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="../plugins/knob/jquery.knob.js"></script>
<script
        src="../plugins/daterangepicker/moment.min.js"></script>
<script
        src="../plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="../plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="../plugins/fastclick/fastclick.js"></script>
<script
        src="../plugins/iCheck/icheck.min.js"></script>
<script
        src="../plugins/adminLTE/js/app.min.js"></script>
<script
        src="../plugins/treeTable/jquery.treetable.js"></script>
<script
        src="../plugins/select2/select2.full.min.js"></script>
<script
        src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="../plugins/ckeditor/ckeditor.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="../plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="../plugins/chartjs/Chart.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="../plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script
        src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

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

    $(document)
        .ready(
            function () {

                // 激活导航位置
                setSidebarActive("admin-datalist");

                // 列表按钮
                $("#dataList td input[type='checkbox']")
                    .iCheck(
                        {
                            checkboxClass: 'icheckbox_square-blue',
                            increaseArea: '20%'
                        });
                // 全选操作
                $("#selall")
                    .click(
                        function () {
                            var clicks = $(this).is(
                                ':checked');
                            if (!clicks) {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck(
                                        "uncheck");
                            } else {
                                $(
                                    "#dataList td input[type='checkbox']")
                                    .iCheck("check");
                            }
                            $(this).data("clicks",
                                !clicks);
                        });
                $("#changePageSize").change(function () {
                    //获取下拉框的值
                    var pageSize = $(this).val();

                    //向服务器发送请求，改变没页显示条数
                    location.href = "${pageContext.request.contextPath}/filelog/findAll?page=${pageInfo.pageNum}&pageSize=" + pageSize;

                });
            });
</script>
</body>

</html>