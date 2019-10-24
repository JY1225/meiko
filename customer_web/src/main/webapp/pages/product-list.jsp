<%@ page import="com.github.pagehelper.PageInfo" %><%--
  Created by IntelliJ IDEA.
  User: 38279
  Date: 2019/3/14
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <title><spring:message code="achievement_system"/></title>
    <meta name="description" content="MEIKO">
    <meta name="keywords" content="MEIKO">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
          
    <style type="text/css">
        #box { font-size: 0.8vw;}
    </style>
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

    <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="${pageContext.request.contextPath}/aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <!-- @@master = admin-layout.html-->
    <!-- @@block = content -->

    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1 class="report_list">
               <!-- 成绩书管理 -->
               <spring:message code="report_list"/>
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/main.jsp"><i class="fa fa-dashboard"></i>
                <!--首页-->
                <spring:message code="home_page"/></a></li>
                <li><!--成绩书管理-->
                <spring:message code="report_list"/></li>
                <li class="active"></li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">
                    <spring:message code="list"/><!-- 列表 --></h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="批量下载" id="btn_del">
                                        <!-- 批量下载 -->
                                       <spring:message code="batch_download"/>
                                    </button>
                                </div>
                            </div>
                        </div> 
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                              <form action="${pageContext.request.contextPath}/file/findAll" method="post" >
                                <label id="box" for="meeting"><spring:message code="shipping_dt"/></label>
                                <label id="box" for="meeting"><spring:message code="begin"/></label>
                                <input id="meeting" type="date" name="fromData" class="date-picker" placeholder="2019/01/01"/>                           
                                <label id="box" for="meeting"><spring:message code="end"/></label>
                                <input id="meeting" type="date" name="toData" class="date-picker" placeholder="2019/01/02"/>
                                 <button type="submit" class="btn btn-default"><spring:message code="Search"/></button>
                             </form>
                            </div>
                        </div>
                        <!--工具栏/-->
                        <form action="${pageContext.request.contextPath}/file/downLoadByNames" method="post" id="formDel" >

                        <!--数据列表-->
                        <table id="dataList"
                               class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px;"><input
                                        id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th id="box" >ID</th>
                                <!-- <th class="" >客户编码</th> -->
                                <th id="box" ><spring:message code="custom"/></th>
                                <th id="box" ><spring:message code="shipping_addr"/></th>
                                <th id="box" ><spring:message code="cust_part"/></th>
                                <th  id="box"><spring:message code="shipping_dt"/></th>
                                <th  id="box"><spring:message code="shipping_no"/></th>                        
                                <%-- <th  id="box"><spring:message code="filename"/></th> --%>
                                <th  id="box"><spring:message code="upload_dt"/></th>   
                                <!-- <th class="text-center" >上传终端</th> -->
                                <th  id="box"><spring:message code="upload_user"/></th>    
                                <th  id="box"><spring:message code="handle"/></th>
                                
                            </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${productPageInfo.list}" var="ofile" varStatus="status">
								 
                                    <tr>                                                                   
                                        <td><input name="names"  type="checkbox" value="${ofile.upload_filename}" id="ids" ></td>
                                        <td id="box">${status.index + 1}</td>
                                       <%--  <td style="font-size:14px">${ofile.cust_code}</td> --%>
                                        <td id="box">${ofile.cust_name}</td>
                                        <td id="box">${ofile.shipping_addr}</td>
                                        <td id="box">${ofile.cust_part}</td>
                                        <td id="box"><fmt:formatDate value='${ofile.shipping_dt}' type="both" /></td>
                                        <td id="box">${ofile.shipping_jccjs_no}</td>
                                        <%-- <td id="box">${ofile.upload_filename}</td> --%>
                                        <td id="box"><fmt:formatDate value='${ofile.upload_dt}' type="both" /></td>
                                        <%-- <td>${product.upload_terminal}</td>  style="font-size:14px" --%>
                                        <td id="box">${ofile.upload_user}</td>
                                        <td id="box">
                                           <%-- <button type="button" class="btn bg-olive btn-xs" onclick="javascript:window.open('${pageContext.request.contextPath}/file/read?upload_filename=${ofile.upload_filename}&recid=${ofile.recid}')" >
                                           <spring:message code="preview"/></button> --%>
                                           <button type="button" class="btn bg-olive btn-xs" onclick="openPostWindow('${pageContext.request.contextPath}/file/read','_blank','${ofile.upload_filename}','${ofile.recid}');" >
                                           <spring:message code="preview"/></button> 
                                           <%--  <button id="download_btn" type="button" class="btn bg-olive btn-xs"  onclick="location.href='${pageContext.request.contextPath}/file/download?upload_filename=${ofile.upload_filename}&recid=${ofile.recid}'" >
                                            <spring:message code="download"/></button> --%> 
                                            <button id="download_btn" type="button" class="btn bg-olive btn-xs"  onclick="openPostWindow('${pageContext.request.contextPath}/file/download','_self','${ofile.upload_filename}','${ofile.recid}');" >
                                            <spring:message code="download"/></button>                                                                         
                                        </td>                                                                        
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                       </form>
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline"> <spring:message code="total"/> ${productPageInfo.pages} <spring:message code="page"/>，
                        <spring:message code="total"/> ${productPageInfo.total} <spring:message code="piece_of_data"/>  <spring:message code="each_page"/> 
                            <select class="form-control" id="changePageSize">

                                <option  <c:if test="${productPageInfo.pageSize==1}">selected</c:if>>1 </option>
                                <option  <c:if test="${productPageInfo.pageSize==5}">selected</c:if>>5</option>
                                <option  <c:if test="${productPageInfo.pageSize==10}">selected</c:if>>10 </option>
                                <option  <c:if test="${productPageInfo.pageSize==15}">selected</c:if>>15</option>
                                <option  <c:if test="${productPageInfo.pageSize==20}">selected</c:if>>20</option>
                            </select> <spring:message code="item"/>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/file/findAll?page=1&pageSize=${productPageInfo.pageSize}" aria-label="Previous">
                            	<spring:message code="first_page"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/file/findAll?page=${productPageInfo.pageNum-1}&pageSize=${productPageInfo.pageSize}">
                            <spring:message code="up_page"/></a></li>

                            <c:if test="${productPageInfo.pages<10}">
                                <c:set var="begin" value="1" />
                                <c:set var="end" value="${productPageInfo.pages}"></c:set>
                                 </c:if>
                            <c:if test="${productPageInfo.pages>10}">
                                <c:set var="begin" value="${productPageInfo.pageNum-4}" />
                                <c:set var="end" value="${productPageInfo.pageNum+5}"></c:set>
                                <c:if test="${begin<0}">
                                <c:set var="begin" value="1" />
                                <c:set var="end" value="${begin+9}"></c:set>
                            </c:if>
                                <c:if test="${end>productPageInfo.pages}">
                                    <c:set var="end" value="${productPageInfo.pages}" />
                                    <c:set var="begin" value="${end-9}"></c:set>
                                </c:if>

                            </c:if>

                                 <c:forEach begin="${begin}" end="${end}" var="i">
                                     <li><a href="${pageContext.request.contextPath}/file/findAll?page=${i}&pageSize=${productPageInfo.pageSize}">${i}</a></li>
                                 </c:forEach>

                            <li><a href="${pageContext.request.contextPath}/file/findAll?page=${productPageInfo.pageNum+1}&pageSize=${productPageInfo.pageSize}">
                            <spring:message code="next_page"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/file/findAll?page=${productPageInfo.pages}&pageSize=${productPageInfo.pageSize}" aria-label="Next">
                            <spring:message code="last_page"/></a></li>
                        </ul>
                    </div>
                </div>
                <!-- /.box-footer-->


            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
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
        src="../plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script
        src="../plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
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
        $("#btn_del").click(function() {
            //alert("123");

            if($("#ids:checked").length>=1){
                
                    $("#formDel").submit();
               

            }else{
                alert("未选择下载项");
            }


        });

        $("#changePageSize").change(function () {
            //获取下拉框的值
            var pageSize = $(this).val();

            //向服务器发送请求，改变没页显示条数
            location.href = "${pageContext.request.contextPath}/file/findAll?page=1&pageSize=" + pageSize;

        });

        // 激活导航位置
        setSidebarActive("admin-datalist");

        // 列表按钮
        $("#dataList td input[type='checkbox']").iCheck({
            checkboxClass: 'icheckbox_square-blue',
            increaseArea: '20%'
        });
        // 全选操作
        $("#selall").click(function () {
            var clicks = $(this).is(':checked');
            if (!clicks) {
                $("#dataList td input[type='checkbox']").iCheck("uncheck");
            } else {
                $("#dataList td input[type='checkbox']").iCheck("check");
            }
            $(this).data("clicks", !clicks);
        });
        
        
        
    });
    
    window.onresize = function(){
        var box = document.getElementById("box");
        box.style["z-index"] = 1;
    }
    
    var defaultDate = document.querySelectorAll('.date-picker');
    for (var i = 0; i<defaultDate.length; i++) {
        defaultDate[i].valueAsDate = new Date();
    }


    function openPostWindow(url, target, upload_filename, recid){
        var tempForm = document.createElement("form");
        tempForm.id = "tempForm1";
        tempForm.method = "post";
        tempForm.action = url;
        tempForm.target=target;
        var hideInput1 = document.createElement("input");
        hideInput1.type = "hidden";
        hideInput1.name="upload_filename";
        hideInput1.value = upload_filename;
        var hideInput2 = document.createElement("input");
        hideInput2.type = "hidden";
        hideInput2.name="recid";
        hideInput2.value = recid;
        tempForm.appendChild(hideInput1);
        tempForm.appendChild(hideInput2);
        if(document.all){
            tempForm.attachEvent("onsubmit",function(){});        //IE
        }else{
            var subObj = tempForm.addEventListener("submit",function(){},false);    //firefox
        }
        document.body.appendChild(tempForm);
        if(document.all){
            tempForm.fireEvent("onsubmit");
        }else{
            tempForm.dispatchEvent(new Event("submit"));
        }
        tempForm.submit();
        document.body.removeChild(tempForm);
    }
     
</script>
</body>

</html>