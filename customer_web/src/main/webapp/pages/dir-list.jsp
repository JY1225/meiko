<%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/16
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title><spring:message code="achievement_system"/></title>
    <meta name="description" content="MEIKO">
    <meta name="keywords" content="MEIKO">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jQuery/layer.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="/header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="/aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
               <spring:message code="resource_directory"/>
                <small><spring:message code="all_directory"/></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/main.jsp"><i
                        class="fa fa-dashboard"></i><spring:message code="home_page"/></a></li>
                <li><spring:message code="resource_directory"/></li>

                <li class="active"><spring:message code="all_directory"/></li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title"><spring:message code="list"/></h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="new"
                                            onclick="location.href='${pageContext.request.contextPath}/pages/dir-add.jsp'">
                                        <i class="fa fa-file-o"></i> <spring:message code="new"/>
                                    </button>

                                    <button type="button" class="btn btn-default" title="refresh" 
                                     onclick="window.location.reload();">
                                        <i class="fa fa-refresh"></i> <spring:message code="refresh"/>
                                    </button>
                                </div>
                            </div>
                        </div>                                           
                       
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <!-- <th class="" style="padding-right: 0px"><input
                                        id="selall" type="checkbox" class="icheckbox_square-blue">
                                        ${pageContext.request.contextPath}/file/dirOnById?id=${dir.id}
                                </th> -->
                                <th class="text-center">ID</th>                                
                                <th class="text-center"><spring:message code="directory"/></th>
                                <th class="text-center"><spring:message code="status"/></th>
                                <th class="text-center"><spring:message code="editor"/></th>
                                <th class="text-center"><spring:message code="handle"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${filePageInfo.list}" var="dir" varStatus="status">
                                <tr>
                                    <!-- <td><input name="ids" type="checkbox"></td> -->
                                    <td class="text-center">${status.index + 1}</td>                                    
                                    <td class="text-center">${dir.url}</td>
                                   <td class="text-center">${dir.statuStr}</td>
                                   <td class="text-center">${dir.editUser}</td>
                                    <td class="text-center">
                                        <a href="javascript:void(0)" class="btn bg-olive btn-xs" onclick="showMess('${ctx}/file/dirOnById',${dir.id},'/file/findAllDir');">
                                        <spring:message code="open"/></a>
                                        <a href="${pageContext.request.contextPath}/file/dirOffById?id=${dir.id}" class="btn bg-olive btn-xs">
                                        <spring:message code="close"/></a>
                                        <a href="${pageContext.request.contextPath}/file/dirDeleById?id=${dir.id}" class="btn bg-olive btn-xs">
										<spring:message code="delete"/></a>
                                    </td>
                                </tr>
                            </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <spring:message code="total"/> ${filePageInfo.pages} <spring:message code="page"/>，
                            <spring:message code="total"/> ${filePageInfo.total} <spring:message code="piece_of_data"/> <spring:message code="each_page"/>
                            <select class="form-control" id="changePageSize">
                            <option <c:if test="${filePageInfo.pageSize==1}">selected</c:if>>1</option>
                            <option <c:if test="${filePageInfo.pageSize==2}">selected</c:if>>2</option>
                            <option <c:if test="${filePageInfo.pageSize==3}">selected</c:if>>3</option>
                            <option <c:if test="${filePageInfo.pageSize==4}">selected</c:if>>4</option>
                            <option <c:if test="${filePageInfo.pageSize==5}">selected</c:if>>5</option>
                        </select> <spring:message code="item"/>
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/file/findAllDir?page=1&pageSize=${filePageInfo.pageSize}" aria-label="Previous">
                            <spring:message code="first_page"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/file/findAllDir?page=${filePageInfo.pageNum-1}&pageSize=${filePageInfo.pageSize}">
                            <spring:message code="up_page"/></a></li>
                            <c:if test="${filePageInfo.pages<10 }">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="${filePageInfo.pages}"></c:set>
                            </c:if>
                            <c:if test="${filePageInfo.pages==10 }">
                                <c:set var="begin" value="1"></c:set>
                                <c:set var="end" value="${filePageInfo.pages}"></c:set>
                            </c:if>
                            <c:if test="${filePageInfo.pages>10}">
                                <c:set var="begin" value="${filePageInfo.pageNum-5}"></c:set>
                                <c:set var="end" value="${filePageInfo.pageNum+4}"></c:set>
                                <c:if test="${begin<0}">
                                    <c:set var="begin" value="1"></c:set>
                                    <c:set var="end" value="${begin+9}"></c:set>
                                </c:if>
                                <c:if test="${end>filePageInfo.pages}">
                                    <c:set var="end" value="${filePageInfo.pages}"></c:set>
                                    <c:set var="begin" value="${end-9}"></c:set>

                                </c:if>
                            </c:if>

                            <c:forEach begin="${begin}" end="${end}" var="i">
                                <li><a href="${pageContext.request.contextPath}/file/findAllDir?page=${i}&pageSize=${filePageInfo.pageSize}">${i}</a></li>
                            </c:forEach>


                            <li><a href="${pageContext.request.contextPath}/file/findAllDir?page=${filePageInfo.pageNum+1}&pageSize=${filePageInfo.pageSize}">
                            <spring:message code="next_page"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/file/findAllDir?page=${filePageInfo.pages}&pageSize=${filePageInfo.pageSize}" aria-label="Next">
                            <spring:message code="last_page"/></a></li>
                        </ul>
                    </div>

                </div>
            </div>

        </section>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <jsp:include page="/footer.jsp"></jsp:include>
    <!-- 底部导航 /-->

</div>

<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<!-- layer -->
<script src="../plugins/jQuery/layer.js"></script>
<script src="../plugins/jQuery/showMess.js"></script>
<script type="text/javascript">
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
                    location.href = "${pageContext.request.contextPath}/file/findAllDir?page=${filePageInfo.pageNum}&pageSize=" + pageSize;

                });
            });
    
    /* function showMess(url,id) {   	
        $.ajax({
            method:"post",
            url:url,
            data:{'id':id},
            success:function(exeDetail){           	
            	if(exeDetail != null && exeDetail != ""){
            	layer.open({
                    type: 1,
                    title:['<span style="color:white;">消息</span> ','background-color: #4898d5' ],
                    area : ['300px ,80px'],
                    shade: 0.5,
                    id: 'LAY_layuipro',
                    moveType: 1,
                    btn: ['确定'],
                 content: '<div id="exe_detail" class="gray-bg  pace-done" style="padding: 50px;  line-height: 22px; font-weight: bold;" ><p style="word-wrap:break-word; word-break:break-all;">'+exeDetail+'</p></div>',
                    closeBtn:1
                  });
            	}else{
            		location='${ctx}/file/findAllDir'; 
            	}
            },
        });
    } */
</script>
</body>

</html>
